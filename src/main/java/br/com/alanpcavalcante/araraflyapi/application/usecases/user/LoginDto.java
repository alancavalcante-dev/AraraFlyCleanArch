package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

public record LoginDto(
        String login,
        String password
) {
}
