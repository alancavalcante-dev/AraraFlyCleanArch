package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

public record CreateUserCommand(
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
