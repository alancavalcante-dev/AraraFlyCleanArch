package br.com.alanpcavalcante.araraflyapi.application.gateways.user;

import br.com.alanpcavalcante.araraflyapi.domain.user.Cpf;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;
import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.Optional;

public interface UserRepository
{
    Optional<User> getUserByLogin(Login login);

    Optional<User> getUserByLoginOrCpfOrEmail(Login login, Cpf cpf, Email email);

    User save(User user);
}
