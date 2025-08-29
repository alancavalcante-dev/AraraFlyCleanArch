package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.deploy;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.DeployRepository;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.DeployMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProjectMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.DeployEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeployRepositoryImpl implements DeployRepository {

    @Autowired
    private DeployRepositoryJpa deployRepositoryJpa;

    @Autowired
    private DeployMapper deployMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Deploy save(Deploy deploy) {
        DeployEntity entity = deployRepositoryJpa.save(deployMapper.domainToEntity(deploy));
        return deployMapper.entityToDomain(entity);
    }

    @Override
    public Optional<Deploy> getDeployByProject(Project project) {
        return deployRepositoryJpa
                .findDeployByProject(projectMapper.domainToEntity(project))
                .map(deployMapper::entityToDomain);
    }

}
