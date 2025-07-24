package br.com.alanpcavalcante.araraflyapi.config;

import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.user.CreateUser;
import br.com.alanpcavalcante.araraflyapi.application.usecases.user.LoginUser;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.domain.user.UserBuild;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user.JpaUserRepository;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public UserBuild userBuild(User user) {
        return new UserBuild(user);
    }

    @Bean
    public UserMapper userMapper(UserBuild userBuild) {
        return new UserMapper(userBuild);
    }

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        return new UserRepositoryImpl(jpaUserRepository, userMapper);
    }

    @Bean
    public CreateUser createUser(UserRepository userRepository, UserBuild userBuilder) {
        return new CreateUser(userRepository, userBuilder);
    }

    @Bean
    public LoginUser loginUser(UserRepository userRepository) {
        return new LoginUser(userRepository);
    }


}