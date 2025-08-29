package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.domain.project.TypePrice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProjectRequest(

    String title,
    String description,
    BigDecimal price,
    TypePrice typePrice,
    LocalDate closingDate,
    LocalDateTime dateCreated

) {
}
