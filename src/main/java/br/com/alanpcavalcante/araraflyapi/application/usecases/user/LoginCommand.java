package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

public record LoginCommand(
        String login,
        String password
) {
}
