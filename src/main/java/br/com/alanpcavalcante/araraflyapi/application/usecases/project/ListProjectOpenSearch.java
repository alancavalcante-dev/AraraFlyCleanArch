package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;

import java.util.List;

public class ListProjectOpenSearch {

    private final ProjectRepository projectRepository;

    public ListProjectOpenSearch(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> list(String search) {
        return projectRepository.listProjectsOpenBySearch(StateBusiness.OPEN, search);
    }

}
