package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.CatalogDeveloperResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.PortfolioDeveloperResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.PortfolioDeveloperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class PortfolioMapper {

    @Autowired
    private UserMapper userMapper;

    public Portfolio entityToDomain(PortfolioDeveloperEntity entity) {
        Portfolio portfolio = new Portfolio();

        portfolio.setIdPortfolio(entity.getIdPortfolio());
        portfolio.setDeveloper(userMapper.entityToDomain(entity.getDeveloper()));
        portfolio.setPresentation(entity.getPresentation());
        portfolio.setResume(entity.getResume());
        portfolio.setDescription(entity.getDescription());
        portfolio.setPictureBanner(Path.of(entity.getPictureBanner()));
        portfolio.setIsPortfolioPublic(entity.getIsPortfolioPublic());
        portfolio.setIsFeedbackPublic(entity.getIsFeedbackPublic());
        portfolio.setIsAverageReviewPublic(entity.getIsAverageReviewPublic());
        return portfolio;
    }

    public PortfolioDeveloperEntity domainToEntity(Portfolio domain) {
        PortfolioDeveloperEntity portfolio = new PortfolioDeveloperEntity();

        portfolio.setIdPortfolio(domain.getIdPortfolio());
        portfolio.setDeveloper(userMapper.domainToEntity(domain.getDeveloper()));
        portfolio.setPresentation(domain.getPresentation());
        portfolio.setResume(domain.getResume());
        portfolio.setDescription(domain.getDescription());
        portfolio.setPictureBanner(domain.getPictureBanner().toString());
        portfolio.setIsPortfolioPublic(domain.getIsPortfolioPublic());
        portfolio.setIsFeedbackPublic(domain.getIsFeedbackPublic());
        portfolio.setIsAverageReviewPublic(domain.getIsAverageReviewPublic());
        return portfolio;
    }

    public PortfolioDeveloperResponse domainToResponsePortfolio(Portfolio portfolio) {
        return new PortfolioDeveloperResponse(
            portfolio.getDeveloper().getProfile().getName().getName(),
            portfolio.getDeveloper().getProfile().getEmail().getEmail(),
            portfolio.getDeveloper().getProfile().getPicture().toString(),

            portfolio.getPresentation(),
            portfolio.getResume(),
            portfolio.getDescription(),
            portfolio.getPictureBanner().toString()
        );
    }

    public CatalogDeveloperResponse domainToCatalogDeveloperResponse(Portfolio portfolio) {
        return new CatalogDeveloperResponse(
                portfolio.getIdPortfolio(),
                portfolio.getDeveloper().getProfile().getName().getName(),
                portfolio.getDeveloper().getProfile().getPicture().toString(),
                portfolio.getTechnologies()
        );
    }
}
