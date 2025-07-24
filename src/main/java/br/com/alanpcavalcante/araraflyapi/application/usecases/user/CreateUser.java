package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.domain.user.*;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.CreateUserDto;

import java.util.Optional;

public class CreateUser {

    private final UserRepository userRepository;
    private final UserBuild userBuilder;

    public CreateUser(UserRepository userRepository, UserBuild userBuilder) {
        this.userRepository = userRepository;
        this.userBuilder = userBuilder;
    }

    public User create(CreateUserDto command) throws Exception {

        User user = userBuilder
                .createLogin(new Login(command.login()), new Password(command.password()))
                .createProfile(
                        new Name(command.name()),
                        new Cpf(command.cpf()),
                        new Email(command.email()),
                        new Phone(command.phone())
                )
                .implementAddress(
                        command.street(),
                        command.city(),
                        command.state(),
                        command.number()
                ).build();


        Optional<User> userDomainOpt = userRepository.getUserByLoginOrCpfOrEmail(user.getLogin(), user.getCpf(), user.getEmail());
        if (userDomainOpt.isPresent()) throw new Exception("Login, Cpf ou Email já cadastrado");

        return userRepository.save(user);
    }

}
