package br.com.alanpcavalcante.araraflyapi.infrastructure.security;

import br.com.alanpcavalcante.araraflyapi.application.usecases.user.GetUser;
import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProfileMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private GetUser getUser;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUser.byLogin(new Login(username));
        UserEntity entity = userMapper.domainToEntity(user);
        entity.setProfile(profileMapper.domainToEntity(user.getProfile()));
        return entity;
    }
}
