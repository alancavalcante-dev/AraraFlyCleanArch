package br.com.alanpcavalcante.araraflyapi.application.usecases.portfolio;

import br.com.alanpcavalcante.araraflyapi.application.gateways.portfolio.PortfolioRepository;
import br.com.alanpcavalcante.araraflyapi.domain.CustomPage;
import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;

public class FindAllDeveloper {

    private final PortfolioRepository portfolioRepository;

    public FindAllDeveloper(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public CustomPage<Portfolio> findAll(Object specification, Object page) {
        return portfolioRepository.listDevelopersPublicBySpec(specification, page);
    }
}
