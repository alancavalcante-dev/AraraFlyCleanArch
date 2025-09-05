package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.CustomPage;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;


public class ListProjectOpenSearch {

    private final ProjectRepository projectRepository;

    public ListProjectOpenSearch(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public CustomPage<Project> listProjects(Object specification, Object page) {
        return projectRepository.listProjectsOpenBySpec(specification, page);
    }

}
