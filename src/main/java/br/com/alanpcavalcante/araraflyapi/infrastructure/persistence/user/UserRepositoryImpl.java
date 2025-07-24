package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user;

import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.domain.user.Cpf;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;
import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.UserMapper;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserMapper mapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> getUserByLogin(Login login) {
        return jpaUserRepository.findByLogin(login.getLogin()).map(mapper::entityToDomain);
    }

    @Override
    public Optional<User> getUserByLoginOrCpfOrEmail(Login login, Cpf cpf, Email email) {
        return jpaUserRepository.findUserByLoginOrCpfOrEmail(login.getLogin(), cpf.getCpf(), email.getEmail())
                .map(mapper::entityToDomain);
    }

    @Override
    public User save(User user) {
        return mapper.entityToDomain(jpaUserRepository.save(mapper.domainToEntity(user)));
    }
}
