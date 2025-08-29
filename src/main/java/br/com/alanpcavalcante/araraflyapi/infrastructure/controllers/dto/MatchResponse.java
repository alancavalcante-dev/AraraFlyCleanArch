package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto;

import java.time.LocalDate;


public record MatchResponse(

        String idMatch,
        String idProject,
        String title,
        String description,

        Boolean confirmationDeveloper,
        Boolean confirmationClient,

        LocalDate closingDate,
        LocalDate dateMatch

) {
}
