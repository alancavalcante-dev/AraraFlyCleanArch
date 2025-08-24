package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public class GetProjectCustomer {

    private final ProjectRepository projectRepository;

    public GetProjectCustomer(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project get(User user, UUID idProject) throws Exception {
        Optional<Project> project = projectRepository.getProjectByIdProjectAndCustomer(idProject, user);
        if (project.isEmpty()) {
            throw new IllegalArgumentException("projeto nao encontrado");
        }
        return project.get();
    }
}
