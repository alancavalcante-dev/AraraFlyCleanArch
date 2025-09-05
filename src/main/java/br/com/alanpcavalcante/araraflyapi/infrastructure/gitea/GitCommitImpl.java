package br.com.alanpcavalcante.araraflyapi.infrastructure.gitea;

import br.com.alanpcavalcante.araraflyapi.application.gateways.commit.GitCommit;
import br.com.alanpcavalcante.araraflyapi.application.usecases.commit.ResponseGetCommits;
import br.com.alanpcavalcante.araraflyapi.application.usecases.commit.ResponsePostCommitData;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class GitCommitImpl implements GitCommit {

    private WebClient webClient;
    private ResponseGetCommits responseGetCommits;

    @Value("${gitea.token}")
    private String giteaToken;

    @Value("${gitea.url}")
    private String giteaUrl;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(giteaUrl + "/api/v1")
                .defaultHeader("Authorization", "token " + giteaToken)
                .build();
    }


    @Override
    public Mono<ResponsePostCommitData> commitData(String repoOwner, String repoName, String filePath, String content, String commitMessage, String branch) {
        String contentBase64 = Base64.getEncoder().encodeToString(content.getBytes());

        responseGetCommits.setMessage(commitMessage);
        responseGetCommits.setContent(contentBase64);
        responseGetCommits.setBranch(branch);

        return this.webClient.post()
                .uri(String.format("/repos/%s/%s/contents/%s", repoOwner, repoName, filePath))
                .bodyValue(responseGetCommits)
                .retrieve()
                .bodyToMono(ResponsePostCommitData.class)
                .doOnSuccess(response -> System.out.println("Commit realizado com sucesso no Gitea! Resposta: " + response))
                .doOnError(error -> System.err.println("Falha ao realizar o commit no Gitea: " + error.getMessage()));
    }


    @Override
    public List<Map<String, Object>> getCommits(String owner, String repository) {
        List<Map<String, Object>> commits = webClient.get()
                .uri("/repos/{owner}/{repo}/commits", owner, repository)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        assert commits != null;
        if (commits.isEmpty()) {
            return List.of();
        }
        return commits;
    }


    @Override
    public List<String> getShaLastCommits(List<Map<String, Object>> commits) {
        Map<String, Object> lastCommit = commits.getLast();
        Map<String, Object> commitDateLast = (Map<String, Object>) lastCommit.get("commit");
        Map<String, Object> authorLast = (Map<String, Object>) commitDateLast.get("author");
        String dateLastCommitStr = (String) authorLast.get("date");
        LocalDate lastCommitDate = ZonedDateTime.parse(dateLastCommitStr).toLocalDate();

        return commits.stream()
                .map(commit -> (Map<String, Object>) commit)
                .filter(commit -> {
                    Map<String, Object> commitInfo = (Map<String, Object>) commit.get("commit");
                    Map<String, Object> author = (Map<String, Object>) commitInfo.get("author");
                    String dateStr = (String) author.get("date");

                    LocalDate commitDate = ZonedDateTime.parse(dateStr).toLocalDate();

                    return commitDate.equals(lastCommitDate);
                })
                .map(commit -> (String) commit.get("sha"))
                .collect(Collectors.toList());
    }

    @Override
    public String getPatchCommits(String username, String repository, List<String> commits) {
        StringBuilder textPatches = new StringBuilder();

        for (String commit : commits) {
            String text = patchCommitBySha(username, repository, commit);
            textPatches.append(text);
        }

        return textPatches.toString();
    }

    @Override
    public String patchCommitBySha(String owner, String repo, String sha) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}/git/commits/{sha}.patch", owner, repo, sha)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
