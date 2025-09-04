package br.com.alanpcavalcante.araraflyapi.application.usecases.portfolio;

import br.com.alanpcavalcante.araraflyapi.application.gateways.portfolio.PortfolioRepository;
import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

public class CreatePortfolioDeveloper {

    private final PortfolioRepository portfolioRepository;

    public CreatePortfolioDeveloper(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio create(User developer) {
        Portfolio portfolio = new Portfolio();

        portfolio.setDeveloper(developer);
        portfolio.setPresentation("Olá, Seja bem-vindo ao meu portfólio");
        portfolio.setIsPortfolioPublic(false);
        portfolio.setIsFeedbackPublic(false);
        portfolio.setIsAverageReviewPublic(false);

        return portfolioRepository.save(portfolio);
    }
}
