package br.com.alanpcavalcante.araraflyapi.infrastructure.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProjectMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.ProjectEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    private ProjectRepositoryJpa projectRepositoryJpa;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public List<Project> getProjectsByCustomerAndStateBusiness(User user, StateBusiness stateBusiness) {
        UserEntity entity = userMapper.domainToEntity(user);
        return projectRepositoryJpa.findProjectsByCustomerAndStateBusiness(entity, stateBusiness)
                .stream().map(projectMapper::entityToDomain).toList();
    }

    @Override
    public List<Project> listProjectsOpenBySearch(StateBusiness stateBusiness, String search) {
        return projectRepositoryJpa.findProjectsByTitleAndStateBusiness(search, stateBusiness)
                .stream().map(projectMapper::entityToDomain).toList();
    }

    @Override
    public Project save(Project project) {
        ProjectEntity entity = projectMapper.domainToEntity(project);
        return projectMapper.entityToDomain(projectRepositoryJpa.save(entity));
    }

    @Override
    public void delete(Project project) {
        ProjectEntity entity = projectMapper.domainToEntity(project);
        projectRepositoryJpa.save(entity);
    }

    @Override
    public Optional<Project> getProjectByIdProjectAndCustomer(UUID id, User customer) {
        UserEntity entity = userMapper.domainToEntity(customer);
        return projectRepositoryJpa.findProjectByIdProjectAndCustomer(id, entity).map(projectMapper::entityToDomain);
    }

    @Override
    public Optional<Project> getProjectByIdProjectAndDeveloper(UUID id, User developer) {
        UserEntity entity = userMapper.domainToEntity(developer);
        return projectRepositoryJpa.findProjectByIdProjectAndDeveloper(id, entity).map(projectMapper::entityToDomain);
    }
}
