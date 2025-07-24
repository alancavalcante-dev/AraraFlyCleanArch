package br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user;

public record CreateUserDto(
        String name,
        String cpf,
        String email,
        String phone,

        String street,
        String city,
        String state,
        String number,

        String login,
        String password
) {
}
