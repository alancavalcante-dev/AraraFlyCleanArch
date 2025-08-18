package br.com.alanpcavalcante.araraflyapi.application.usecases.pointmarking;

import java.time.LocalDateTime;

public record PointMarkingRequest(
        String comment,

        String commentsGeneratedIA,

        LocalDateTime dateStarting,

        LocalDateTime dateClosing
) {
}
