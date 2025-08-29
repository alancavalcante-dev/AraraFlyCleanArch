package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto;

import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.project.TypePrice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProjectResponse(

        String idProject,
        String title,
        String description,
        BigDecimal price,
        TypePrice typePrice,
        LocalDate closingDate,
        StateBusiness stateBusiness
//        Integer projectsDone
) {
}

