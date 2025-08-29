package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.CustomPage;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProjectMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.ProjectEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    private ProjectRepositoryJpa projectRepositoryJpa;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public CustomPage<Project> listProjectsOpenBySpec(Object specification, Object page) {
        Specification<ProjectEntity> spec = (Specification<ProjectEntity>) specification;

        Page<ProjectEntity> entityPage = projectRepositoryJpa.findAll(spec, (Pageable) page);

        List<Project> projects = entityPage.getContent().stream()
                .map(projectMapper::entityToDomain)
                .toList();

        return new CustomPage<>(
                projects,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getSort()
        );
    }

    @Override
    public List<Project> getProjectsByCustomerAndStateBusiness(User user, StateBusiness stateBusiness) {
        UserEntity entity = userMapper.domainToEntity(user);
        return projectRepositoryJpa.findProjectsByCustomerAndStateBusiness(entity, stateBusiness)
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
        projectRepositoryJpa.delete(entity);
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
