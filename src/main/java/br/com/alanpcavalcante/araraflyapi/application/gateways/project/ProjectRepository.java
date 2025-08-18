package br.com.alanpcavalcante.araraflyapi.application.gateways.project;

import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {

    Optional<Project> getProjectById(User user, UUID idProject);

    List<Project> getProjectsOpenByUserAndStateBusiness(User user, StateBusiness stateBusiness);

    List<Project> listProjectsOpenBySearch(StateBusiness stateBusiness, String search);

    Project save(Project project);

}
