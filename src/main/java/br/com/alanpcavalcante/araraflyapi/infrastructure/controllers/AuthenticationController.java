package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;


import br.com.alanpcavalcante.araraflyapi.application.usecases.user.CreateUser;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.AuthMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.security.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@Tag(name="Autenticação")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final CreateUser createUser;
    private final AuthMapper authMapper;
    private final TokenServiceImpl tokenServiceImpl;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenServiceImpl.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CreateUserDto data) throws Exception {
        User user = authMapper.toUserDomain(data);
        createUser.create(user);
        return ResponseEntity.ok().build();
    }

}
