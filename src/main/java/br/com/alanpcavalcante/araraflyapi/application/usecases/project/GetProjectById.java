package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.ProjectNotFound;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;

import java.util.UUID;

public class GetProjectById {

    private final ProjectRepository projectRepository;

    public GetProjectById(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project get(UUID id) {
        return projectRepository.getProjectById(id).orElseThrow(() -> new ProjectNotFound("Project not found"));
    }
}
