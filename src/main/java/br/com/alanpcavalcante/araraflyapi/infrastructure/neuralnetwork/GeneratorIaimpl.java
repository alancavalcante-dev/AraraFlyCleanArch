package br.com.alanpcavalcante.araraflyapi.infrastructure.neuralnetwork;

import br.com.alanpcavalcante.araraflyapi.application.gateways.neuralnetwork.GeneratorIA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GeneratorIaimpl implements GeneratorIA {

    private String host;
    private String model;
    private String token;
    private PromptGeneratorComment promptGeneratorComment;
    private final WebClient webClient;

    public GeneratorIaimpl(
            @Value("${neuralnetwork.host}") String host, @Value("${neuralnetwork.model}") String model, @Value("${neuralnetwork.token}") String token,
            PromptGeneratorComment promptGeneratorComment) {

        this.webClient = WebClient.builder()
                .baseUrl(host)
                .defaultHeader("Content-Type", "application/json")
                .build();
        this.host = host;
        this.model = model;
        this.token = token;
    }

    @Override
    public CompletableFuture<String> generatedCommentIA(String patchCommit, String comment, LocalDateTime dateStarting, LocalDateTime dateEnding) {
        String prompt = promptGeneratorComment.prompt(patchCommit, comment, dateStarting, dateEnding);

        GeminiRequest.Part part = new GeminiRequest.Part();
        part.setText(prompt);

        GeminiRequest.Content content = new GeminiRequest.Content();
        content.setParts(List.of(part));

        GeminiRequest requestBody = new GeminiRequest();
        requestBody.setModel("models/" + this.model);
        requestBody.setContents(List.of(content));

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/" + this.model + ":generateContent")
                        .queryParam("key", this.token)
                        .build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .map(resp -> resp.getCandidates().get(0).getContent().getParts().get(0).getText())
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)))
                .toFuture();
    }
}
