package br.com.alanpcavalcante.araraflyapi.infrastructure.gateways;

import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.domain.user.Cpf;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;
import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> getUserByLogin(Login login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByLoginOrCpfOrEmail(Login login, Cpf cpf, Email email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }
}
