package br.com.alanpcavalcante.araraflyapi.infrastructure.gateways;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjectRepositoryImpl implements ProjectRepository {

    @Override
    public Optional<Project> getProjectById(User user, UUID idProject) {
        return Optional.empty();
    }

    @Override
    public List<Project> getProjectsOpenByUserAndStateBusiness(User user, StateBusiness stateBusiness) {
        return List.of();
    }

    @Override
    public List<Project> listProjectsOpenBySearch(StateBusiness stateBusiness, String search) {
        return List.of();
    }

    @Override
    public Project save(Project project) {
        return null;
    }
}
