package br.com.alanpcavalcante.araraflyapi.infrastructure.controller;


import br.com.alanpcavalcante.araraflyapi.application.usecases.user.CreateUser;
import br.com.alanpcavalcante.araraflyapi.application.usecases.user.LoginUser;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.CreateUserDto;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.LoginDto;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private CreateUser createUser;

    @Autowired
    private LoginUser loginUser;

    @Autowired
    private UserMapper userMapper;


    @PostMapping("register")
    public ResponseEntity<Void> registerUser(@RequestBody CreateUserDto dto) throws Exception {
        createUser.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    public ResponseEntity<Void> loginUser(@RequestBody LoginDto dto) throws Exception {
        if (loginUser.login(dto)) {
            return ResponseEntity.ok().build();
        } return ResponseEntity.notFound().build();

    }

}
