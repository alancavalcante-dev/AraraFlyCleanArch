package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.ProjectNotStating;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.UserDoesNotBelong;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.UUID;

public class DeleteProjectCustomer {

    private final ProjectRepository projectRepository;

    public DeleteProjectCustomer(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void delete(UUID idProject, User customer) {
        Project project = projectRepository.getProjectByIdProjectAndCustomer(idProject, customer)
                .orElseThrow(() -> new UserDoesNotBelong("User does not belong"));

        StateBusiness state = project.getStateBusiness();
        if (state == StateBusiness.WORKING || state == StateBusiness.DIDNTSTART) {
            throw new ProjectNotStating("You can only delete when the project has not been started");
        }
       projectRepository.delete(project);
    }
}
