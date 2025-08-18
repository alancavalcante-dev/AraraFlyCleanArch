package br.com.alanpcavalcante.araraflyapi.infrastructure.deploy;

import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.DeployEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeployRepositoryJpa extends JpaRepository<DeployEntity, UUID> {

    Optional<DeployEntity> findDeployByProject(ProjectEntity project);

}
