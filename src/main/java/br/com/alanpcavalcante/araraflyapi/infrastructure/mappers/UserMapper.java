package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;


import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.Password;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User entityToDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setLogin(new Login(entity.getLogin()));
        user.setPassword(new Password(entity.getPassword()));
        user.setRole(entity.getRole());
        user.setIsDeveloper(entity.getIsDeveloper());
        return user;
    }

    public UserEntity domainToEntity(User domain) {
        UserEntity userEntity = new UserEntity();

        if (domain.getId() != null) {
            userEntity.setId(domain.getId());
        }

        userEntity.setLogin(domain.getLogin().getLogin());
        userEntity.setPassword(domain.getPassword().getPassword());
        userEntity.setRole(domain.getRole());
        userEntity.setIsDeveloper(domain.getIsDeveloper());
        return userEntity;
    }
}
