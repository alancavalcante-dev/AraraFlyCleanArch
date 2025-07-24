package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.LoginDto;

public class LoginUser {

    private UserRepository userRepository;

    public boolean login(LoginDto command) throws Exception {
        User user = userRepository.getUserByLogin(new Login(command.login()))
                .orElseThrow(() -> new Exception("Usuário não encontrado com o login informado"));

        if (user.getPassword().getPassword().equals(command.password())) {
            return true;
        } return false;
    }
}
