package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

public record CreateUserDto(
        String name,
        String cpf,
        String email,
        String phone,

        String street,
        String city,
        String state,
        Integer number,

        String login,
        String password,
        Boolean isDeveloper
) {
}
