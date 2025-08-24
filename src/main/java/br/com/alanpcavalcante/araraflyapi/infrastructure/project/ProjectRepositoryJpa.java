package br.com.alanpcavalcante.araraflyapi.infrastructure.project;

import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.ProjectEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProjectRepositoryJpa extends JpaRepository<ProjectEntity, UUID>{

    Optional<ProjectEntity> findProjectByIdProjectAndCustomer(UUID idProject, UserEntity customer);
    Optional<ProjectEntity> findProjectByIdProjectAndDeveloper(UUID idProject, UserEntity developer);

    List<ProjectEntity> findProjectsByCustomerAndStateBusiness(UserEntity customer, StateBusiness stateBusiness);
    List<ProjectEntity> findProjectsByTitleAndStateBusiness(String search, StateBusiness stateBusiness);

}
