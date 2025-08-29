package br.com.alanpcavalcante.araraflyapi.infrastructure.handler;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import br.com.alanpcavalcante.araraflyapi.domain.exceptions.Error;

@JsonPropertyOrder({"error"})
public record ApiErrorResponse(Error error) {
}