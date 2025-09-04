package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.match;

import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.MatchEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface MatchRepositoryJpa extends JpaRepository<MatchEntity, UUID> {

    @Query("Select m from MatchEntity m Where m.project.customer.id = :id Or m.developer.id = :id")
    Optional<MatchEntity> getMatchByCustomerOrDeveloper(UUID id);

    @Query("Select m From MatchEntity m Where m.developer.id = :idDeveloper and m.project.stateBusiness = :stateBusiness")
    List<MatchEntity> getAllMatchByDeveloperAndStateBusiness(UUID idDeveloper, StateBusiness stateBusiness);

    @Query("Select m From MatchEntity m Where m.project.customer.id = :idCustomer and m.project.stateBusiness = :stateBusiness")
    List<MatchEntity> getAllMatchByCustomerAndStateBusiness(UUID idCustomer, StateBusiness stateBusiness);

}
