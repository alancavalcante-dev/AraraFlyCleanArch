package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findUserByLoginOrCpfOrEmail(String login, String cpf, String email);


}