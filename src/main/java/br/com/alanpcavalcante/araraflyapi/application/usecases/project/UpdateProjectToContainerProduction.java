package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.Notification;
import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.TextField;
import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.deploy.CreateDeploy;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

public class UpdateProjectToContainerProduction {

    private final ProjectRepository projectRepository;
    private final Notification notification;
    private final CreateDeploy createDeploy;

    public UpdateProjectToContainerProduction(ProjectRepository projectRepository, Notification notification, CreateDeploy createDeploy) {
        this.projectRepository = projectRepository;
        this.notification = notification;
        this.createDeploy = createDeploy;
    }

    public void updateProject(Project project, User developer) {
        project.setStateBusiness(StateBusiness.DIDNTSTART);
        project.setDeveloper(developer);

        Email customerEmail = project.getDeveloper().getProfile().getEmail();
        Email developerEmail = developer.getProfile().getEmail();
        TextField textField = new TextField("Projeto movido para Produção: Container - " + project.getTitle());
        TextField bodyField = new TextField("Container criado, clique no link para ser redirecionado para os detalhes do projeto: (link)");

        projectRepository.save(project);

        createDeploy.create(project);

        notification.send(customerEmail, textField, bodyField);
        notification.send(developerEmail, textField, bodyField);
    }

}
