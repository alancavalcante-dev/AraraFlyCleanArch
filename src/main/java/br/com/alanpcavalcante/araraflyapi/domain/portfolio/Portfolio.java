package br.com.alanpcavalcante.araraflyapi.domain.portfolio;

import br.com.alanpcavalcante.araraflyapi.domain.user.User;


import java.util.UUID;

public class Portfolio {

    private UUID idPortfolio;

    private String presentation;

    private String resume;

    private User userDeveloper;

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

    public User getUserDeveloper() {
        return userDeveloper;
    }

    public void setUserDeveloper(User userDeveloper) {
        this.userDeveloper = userDeveloper;
    }
}
