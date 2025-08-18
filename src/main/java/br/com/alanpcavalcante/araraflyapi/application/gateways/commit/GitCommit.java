package br.com.alanpcavalcante.araraflyapi.application.gateways.commit;

import br.com.alanpcavalcante.araraflyapi.application.usecases.commit.ResponsePostCommitData;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface GitCommit {

    Mono<ResponsePostCommitData> commitData(String repoOwner, String repoName, String filePath, String content, String commitMessage, String branch);

    List<Map<String, Object>> getCommits(String owner, String repository);

    List<String> getShaLastCommits(List<Map<String, Object>> commits);

    String getPatchCommits(String username, String repository, List<String> commits);

    String patchCommitBySha(String owner, String repo, String sha);
}
