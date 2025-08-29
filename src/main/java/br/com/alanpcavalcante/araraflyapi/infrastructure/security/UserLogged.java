package br.com.alanpcavalcante.araraflyapi.infrastructure.security;

import br.com.alanpcavalcante.araraflyapi.application.usecases.user.GetUser;
import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserLogged {

    // Injetando o repositório para poder buscar o usuário
    private final GetUser getUser;
    private final UserMapper userMapper;

    // Use injeção por construtor
    public UserLogged(GetUser getUser, UserMapper userMapper) {
        this.getUser = getUser;
        this.userMapper = userMapper;
    }

    public User load() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof String login) {
            return getUser.byLogin(new Login(login));
        }

        if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
            return userMapper.entityToDomain((UserEntity) authentication.getPrincipal());
        }

        throw new UsernameNotFoundException("Usuário não autenticado ou tipo de Principal desconhecido");
    }
}
