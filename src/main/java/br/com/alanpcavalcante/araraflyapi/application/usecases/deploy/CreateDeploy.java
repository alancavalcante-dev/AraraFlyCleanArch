package br.com.alanpcavalcante.araraflyapi.application.usecases.deploy;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.DeployRepository;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.*;
import br.com.alanpcavalcante.araraflyapi.domain.languages.Javascript;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.services.Static;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;

import java.util.ArrayList;

public class CreateDeploy {

    private final Deploy deploy;
    private final DeployRepository deployRepository;

    public CreateDeploy(Deploy deploy, DeployRepository deployRepository) {
        this.deploy = deploy;
        this.deployRepository = deployRepository;
    }

    public void create(Project project) {
        deploy.setSurnameService("meu-app");
        deploy.setTypeService(new Static());
        deploy.setLanguage(new Javascript());
        deploy.setUp(false);
        deploy.setProject(project);
        deploy.setPortsExposes(new ArrayList<>());
        deploy.setVariableEnvironments(new ArrayList<>());

        deployRepository.save(deploy);
    }
}
