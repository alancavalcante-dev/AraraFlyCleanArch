package br.com.alanpcavalcante.araraflyapi.infrastructure.profile;


import br.com.alanpcavalcante.araraflyapi.infrastructure.model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface ProfileRepositoryJpa extends JpaRepository<ProfileEntity, UUID> {

    Optional<ProfileEntity> findProfileByEmailOrCpf(String email, String cpf);

}
