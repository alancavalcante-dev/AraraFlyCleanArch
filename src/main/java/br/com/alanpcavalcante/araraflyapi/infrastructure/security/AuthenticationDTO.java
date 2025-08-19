package br.com.alanpcavalcante.araraflyapi.infrastructure.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull @NotBlank String login,
        @NotNull @NotBlank String password
) {
}
