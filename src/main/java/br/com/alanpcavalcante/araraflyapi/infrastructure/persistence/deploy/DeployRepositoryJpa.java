package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.deploy;

import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.DeployEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeployRepositoryJpa extends JpaRepository<DeployEntity, UUID> {

    Optional<DeployEntity> findDeployByProject(ProjectEntity project);

}
