package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.PortfolioDeveloperEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class PortfolioMapper {

    @Autowired
    private UserMapper userMapper;

    public Portfolio entityToDomain(PortfolioDeveloperEntity entity) {
        Portfolio portfolio = new Portfolio();
        portfolio.setIdPortfolio(entity.getIdPortfolio());
        portfolio.setPresentation(entity.getPresentation());
        portfolio.setResume(entity.getResume());
        portfolio.setUserDeveloper(userMapper.entityToDomain(entity.getUserDeveloper()));
        return portfolio;
    }

    public PortfolioDeveloperEntity domainToEntity(Portfolio domain) {
        PortfolioDeveloperEntity portfolio = new PortfolioDeveloperEntity();
        portfolio.setIdPortfolio(domain.getIdPortfolio());
        portfolio.setPresentation(domain.getPresentation());
        portfolio.setResume(domain.getResume());
        portfolio.setUserDeveloper(userMapper.domainToEntity(domain.getUserDeveloper()));
        return portfolio;
    }
}
