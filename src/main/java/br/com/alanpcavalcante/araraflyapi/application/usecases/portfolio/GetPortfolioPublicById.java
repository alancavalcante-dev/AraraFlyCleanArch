package br.com.alanpcavalcante.araraflyapi.application.usecases.portfolio;

import br.com.alanpcavalcante.araraflyapi.application.gateways.portfolio.PortfolioRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.PortfolioInvalid;
import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;

import java.util.UUID;

public class GetPortfolioPublicById {

    private final PortfolioRepository portfolioRepository;

    public GetPortfolioPublicById(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio get(UUID idPortfolio) {
         return portfolioRepository.findPortfolioByIdPortfolioAndIsPortfolioPublic(idPortfolio, true)
                 .orElseThrow(() -> new PortfolioInvalid("portfolio not found"));
    }
}
