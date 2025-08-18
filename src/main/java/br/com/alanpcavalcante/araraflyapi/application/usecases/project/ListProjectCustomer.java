package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;

public class ListProjectCustomer {

    private final ProjectRepository projectRepository;

    public ListProjectCustomer(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> listOpen(User user) {
        return projectRepository.getProjectsOpenByUserAndStateBusiness(user, StateBusiness.OPEN);
    }

    public List<Project> listFinished(User user) {
        return projectRepository.getProjectsOpenByUserAndStateBusiness(user, StateBusiness.FINISHED);
    }

}

