package br.com.alanpcavalcante.araraflyapi.infrastructure.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotNull @NotBlank
        String name,

        @NotNull @NotBlank
        String cpf,

        @NotNull @NotBlank
        String email,

        @NotNull @NotBlank
        String phone,

        @NotNull @NotBlank
        String state,

        @NotNull @NotBlank
        String city,

        String street,
        Integer number,

        @NotNull @NotBlank
        String login,

        @NotNull @NotBlank
        String password,

        @NotBlank
        Boolean isDeveloper
) {
}
