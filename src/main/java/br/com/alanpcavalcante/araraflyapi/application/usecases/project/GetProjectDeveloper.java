package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.ProjectNotFound;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.Optional;
import java.util.UUID;


public class GetProjectDeveloper {

    private final ProjectRepository projectRepository;

    public GetProjectDeveloper(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Optional<Project> get(User user, UUID idProject) {
        Optional<Project> project = projectRepository.getProjectByIdProjectAndDeveloper(idProject, user);
        if (project.isEmpty()) {
            throw new ProjectNotFound("Project not found");
        }
        return project;
    }
}