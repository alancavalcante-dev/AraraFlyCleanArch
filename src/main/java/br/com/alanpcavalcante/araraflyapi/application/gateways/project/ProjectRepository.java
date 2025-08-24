package br.com.alanpcavalcante.araraflyapi.application.gateways.project;

import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {

    Project save(Project project);
    void delete(Project project);
    List<Project> listProjectsOpenBySearch(StateBusiness stateBusiness, String search);

    Optional<Project> getProjectByIdProjectAndCustomer(UUID id, User customer);
    List<Project> getProjectsByCustomerAndStateBusiness(User customer, StateBusiness stateBusiness);

    Optional<Project> getProjectByIdProjectAndDeveloper(UUID id, User developer);

}
