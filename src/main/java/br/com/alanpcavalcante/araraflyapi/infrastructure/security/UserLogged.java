package br.com.alanpcavalcante.araraflyapi.infrastructure.security;

import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserLogged {

    @Autowired
    private UserMapper userMapper;

    public User load() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
            return userMapper.entityToDomain((UserEntity) authentication.getPrincipal());
        }
        throw new UsernameNotFoundException("Usuário não autenticado");
    }
}
