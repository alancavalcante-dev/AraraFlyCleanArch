package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user;

import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserByLogin(String login);

}
