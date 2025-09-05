package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.portfolio;

import br.com.alanpcavalcante.araraflyapi.application.gateways.portfolio.PortfolioRepository;
import br.com.alanpcavalcante.araraflyapi.domain.CustomPage;
import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.PortfolioMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.PortfolioDeveloperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public CustomPage<Portfolio> listDevelopersPublicBySpec(Object search, Object page) {
        Specification<PortfolioDeveloperEntity> spec = (Specification<PortfolioDeveloperEntity>) search;

        Page<PortfolioDeveloperEntity> entityPage = portfolioRepositoryJpa.findAll(spec, (Pageable) page);

        List<Portfolio> portfolios = entityPage.getContent().stream()
                .map(portfolioMapper::entityToDomain)
                .toList();

        return new CustomPage<>(
                portfolios,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getSort()
        );
    }
}
