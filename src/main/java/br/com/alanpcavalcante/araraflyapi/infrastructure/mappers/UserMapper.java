package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;


import br.com.alanpcavalcante.araraflyapi.domain.user.Login;
import br.com.alanpcavalcante.araraflyapi.domain.user.Password;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
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
        UserEntity user = new UserEntity();
        user.setId(domain.getId());
        user.setLogin(domain.getLogin().getLogin());
        user.setPassword(domain.getPassword().getPassword());
        user.setRole(domain.getRole());
        user.setIsDeveloper(domain.getIsDeveloper());
        return user;
    }
}
