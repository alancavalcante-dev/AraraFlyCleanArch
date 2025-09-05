package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.ProjectNotFound;
import br.com.alanpcavalcante.araraflyapi.domain.exceptions.StateBusinessInvalid;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;

import java.util.UUID;

public class GetProjectByIdOpen {

    private final ProjectRepository projectRepository;

    public GetProjectByIdOpen(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project get(UUID id) {
        Project project = projectRepository.getProjectById(id).orElseThrow(() -> new ProjectNotFound("project not found"));

        if (project.getStateBusiness() != StateBusiness.OPEN) {
            throw new StateBusinessInvalid("project state no open");
        }
        return project;
    }
}
