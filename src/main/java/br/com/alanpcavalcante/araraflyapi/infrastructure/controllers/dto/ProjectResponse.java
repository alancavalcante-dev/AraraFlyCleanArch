package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectResponse(
        String idProject,
        String title,
        String description,
        BigDecimal price,
        String typePrice,
        LocalDate closingDate,

        String nameClient,
        LocalDate dateCreated,
        Integer projectsDone
) {
}

