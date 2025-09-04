package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.portfolio;

import br.com.alanpcavalcante.araraflyapi.application.gateways.portfolio.PortfolioRepository;
import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.PortfolioMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.PortfolioDeveloperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PortfolioRepositoryImpl implements PortfolioRepository {

    @Autowired
    private PortfolioMapper portfolioMapper;

    @Autowired
    private PortfolioRepositoryJpa portfolioRepositoryJpa;

    @Override
    public Portfolio save(Portfolio portfolio) {
        PortfolioDeveloperEntity entity = portfolioRepositoryJpa.save(portfolioMapper.domainToEntity(portfolio));
        return portfolioMapper.entityToDomain(entity);
    }

    @Override
    public Optional<Portfolio> findPortfolioByIdPortfolioAndIsPortfolioPublic(UUID id, Boolean isPublic) {
        return portfolioRepositoryJpa.findPortfolioByIdPortfolioAndIsPortfolioPublic(id, isPublic).map(portfolioMapper::entityToDomain);
    }
}
