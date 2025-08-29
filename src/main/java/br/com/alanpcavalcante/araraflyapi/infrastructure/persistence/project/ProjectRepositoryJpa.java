package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.project;

import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.ProjectEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProjectRepositoryJpa extends JpaRepository<ProjectEntity, UUID>, JpaSpecificationExecutor<ProjectEntity> {

    Optional<ProjectEntity> findProjectByIdProjectAndCustomer(UUID idProject, UserEntity customer);
    Optional<ProjectEntity> findProjectByIdProjectAndDeveloper(UUID idProject, UserEntity developer);

    List<ProjectEntity> findProjectsByCustomerAndStateBusiness(UserEntity customer, StateBusiness stateBusiness);

}
