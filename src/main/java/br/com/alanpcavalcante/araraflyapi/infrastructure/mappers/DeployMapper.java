package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Environment;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.PortExpose;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.DeployEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.EnvironmentEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.PortExposeEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class DeployMapper {

    private ProjectMapper projectMapper;

    public Deploy entityToDomain(DeployEntity entity) {
        if (entity == null) return null;

        Deploy deploy = new Deploy();
        deploy.setIdDeploy(entity.getIdDeploy());
        deploy.setSurnameService(entity.getSurnameService());
//        deploy.setTypeService(entity.getTypeService().getService());
//        deploy.setLanguage(entity.getLanguage());
        deploy.setLanguageVersion(entity.getLanguageVersion());
        deploy.setEntrypoint(entity.getEntrypoint());

        deploy.setPortsExposes(
                Optional.ofNullable(entity.getPortsExposes())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(p -> new PortExpose(p.getIdPortExpose(), p.getPort(), null))
                        .collect(Collectors.toList())
        );

        deploy.setVariableEnvironments(
                Optional.ofNullable(entity.getVariableEnvironments())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(e -> new Environment(e.getIdEnvironment(), e.getKey(), e.getValue()))
                        .collect(Collectors.toList())
        );

        deploy.setUp(entity.getIsUp());
        deploy.setProject(projectMapper.entityToDomain(entity.getProject()));
        deploy.setPath(null);

        return deploy;
    }

    public DeployEntity domainToEntity(Deploy deploy) {
        if (deploy == null) return null;

        DeployEntity entity = new DeployEntity();
        entity.setIdDeploy(deploy.getIdDeploy());
        entity.setSurnameService(deploy.getSurnameService());
//        entity.setTypeService(deploy.getTypeService());
//        entity.setLanguage(deploy.getLanguage());
        entity.setLanguageVersion(deploy.getLanguageVersion());
        entity.setEntrypoint(deploy.getEntrypoint());

        entity.setPortsExposes(
                Optional.ofNullable(deploy.getPortsExposes())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(p -> {
                            PortExposeEntity portEntity = new PortExposeEntity();
                            portEntity.setIdPortExpose(p.getIdPortExpose());
                            portEntity.setPort(p.getPort());
                            portEntity.setDeploy(entity);
                            return portEntity;
                        })
                        .collect(Collectors.toList())
        );

        entity.setVariableEnvironments(
                Optional.ofNullable(deploy.getVariableEnvironments())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(e -> {
                            EnvironmentEntity envEntity = new EnvironmentEntity();
                            envEntity.setIdEnvironment(e.getIdEnvironment());
                            envEntity.setKey(e.getKey());
                            envEntity.setValue(e.getValue());
                            envEntity.setDeploy(entity);
                            return envEntity;
                        })
                        .collect(Collectors.toList())
        );

        entity.setIsUp(deploy.getUp());
        entity.setProject(projectMapper.domainToEntity(deploy.getProject()));

        return entity;
    }
}

