package br.com.alanpcavalcante.araraflyapi.infrastructure.user;

import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProfileMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.ProfileEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfileMapper profileMapper;


    @Override
    public Optional<User> getUserByLogin(Login login) {
        return userRepositoryJpa.findUserByLogin(login.getLogin()).map( u -> {
            User user = userMapper.entityToDomain(u);
            user.setProfile(profileMapper.entityToDomain(u.getProfile()));
            return user;
        });

    }

    @Override
    @Transactional
    public User save(User domain) {
        UserEntity user = userMapper.domainToEntity(domain);
        ProfileEntity profile = profileMapper.domainToEntity(domain.getProfile());
        user.setProfile(profile);
        return userMapper.entityToDomain(userRepositoryJpa.save(user));
    }

}
