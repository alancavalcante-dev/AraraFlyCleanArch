package br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user;

public record LoginDto(
        String login,
        String password
) {
}
