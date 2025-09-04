package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.portfolio;

import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.PortfolioDeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PortfolioRepositoryJpa extends JpaRepository<PortfolioDeveloperEntity, UUID> {

    Optional<PortfolioDeveloperEntity> findPortfolioByIdPortfolioAndIsPortfolioPublic(UUID id, Boolean isPublic);
}
