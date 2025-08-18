package br.com.alanpcavalcante.araraflyapi.application.gateways.neuralnetwork;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public interface GeneratorIA {

    CompletableFuture<String> generatedCommentIA(String patchCommit, String comment, LocalDateTime dateStarting, LocalDateTime dateEnding);

}
