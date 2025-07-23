package br.com.alanpcavalcante.araraflyapi.infrastructure.controller;

import br.com.alanpcavalcante.araraflyapi.application.usecases.user.CreateUser;
import br.com.alanpcavalcante.araraflyapi.application.usecases.user.CreateUserCommand;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final CreateUser createUser;

    public UserController(CreateUser createUser, UserRepository repository) {
        this.createUser = createUser;
    }

    public ResponseEntity registerUser(CreateUserCommand dto) throws Exception {
        User user = createUser.create(dto);
    }
}
