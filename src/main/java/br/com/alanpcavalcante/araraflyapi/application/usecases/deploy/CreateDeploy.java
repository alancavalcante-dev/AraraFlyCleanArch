package br.com.alanpcavalcante.araraflyapi.application.usecases.deploy;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.DeployRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.Notification;
import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.TextField;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.*;
import br.com.alanpcavalcante.araraflyapi.domain.languages.Javascript;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.services.Static;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.Title;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;

import java.util.ArrayList;

public class CreateDeploy {

    private final Deploy deploy;
    private final DeployRepository deployRepository;
    private final Notification notification;


    public CreateDeploy(Deploy deploy, DeployRepository deployRepository, Notification notification) {
        this.deploy = deploy;
        this.deployRepository = deployRepository;
        this.notification = notification;
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

        Email email = project.getDeveloper().getProfile().getEmail();
        notification.send(email, subjectMessage(project.getTitle()), bodyMessage());
    }

    private TextField subjectMessage(Title title) {
        return new TextField("Repositório do projeto "+ title.getTitle() +" criado com sucesso - Arara Fly");
    }

    private TextField bodyMessage() {
        return new TextField("Clique no link ao lado para fazer seu acesso ao repositório: (link)");
    }

}
