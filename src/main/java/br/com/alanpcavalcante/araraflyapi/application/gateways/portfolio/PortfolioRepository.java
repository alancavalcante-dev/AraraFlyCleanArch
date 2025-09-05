package br.com.alanpcavalcante.araraflyapi.application.gateways.portfolio;


import br.com.alanpcavalcante.araraflyapi.domain.CustomPage;
import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;

import java.util.Optional;
import java.util.UUID;

public interface PortfolioRepository {

    Portfolio save(Portfolio portfolio);
    Optional<Portfolio> findPortfolioByIdPortfolioAndIsPortfolioPublic(UUID id, Boolean isPublic);
    CustomPage<Portfolio> listDevelopersPublicBySpec(Object search, Object page);

}
