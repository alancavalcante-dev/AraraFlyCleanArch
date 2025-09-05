package br.com.alanpcavalcante.araraflyapi.domain.portfolio;

import br.com.alanpcavalcante.araraflyapi.domain.technology.Technology;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;


import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public class Portfolio {

    private UUID idPortfolio;
    private User developer;
    private String presentation;
    private String resume;
    private String description;
    private Path pictureBanner;
    private Boolean isPortfolioPublic;
    private Boolean isAverageReviewPublic;
    private Boolean isFeedbackPublic;
    private List<Technology> technologies;

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }


    public UUID getIdPortfolio() {
        return idPortfolio;
    }

    public void setIdPortfolio(UUID idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Path getPictureBanner() {
        return pictureBanner;
    }

    public void setPictureBanner(Path pictureBanner) {
        this.pictureBanner = pictureBanner;
    }

    public Boolean getIsPortfolioPublic() {
        return isPortfolioPublic;
    }

    public void setIsPortfolioPublic(Boolean portfolioPublic) {
        isPortfolioPublic = portfolioPublic;
    }

    public Boolean getIsAverageReviewPublic() {
        return isAverageReviewPublic;
    }

    public void setIsAverageReviewPublic(Boolean averageReviewPublic) {
        isAverageReviewPublic = averageReviewPublic;
    }

    public Boolean getIsFeedbackPublic() {
        return isFeedbackPublic;
    }

    public void setIsFeedbackPublic(Boolean feedbackPublic) {
        isFeedbackPublic = feedbackPublic;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}
