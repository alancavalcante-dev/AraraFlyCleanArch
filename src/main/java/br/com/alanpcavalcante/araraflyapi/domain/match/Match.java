package br.com.alanpcavalcante.araraflyapi.domain.match;

import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.time.LocalDate;
import java.util.UUID;


public class Match {

    private UUID idMatch;

    private Project project;

    private User developer;

    private Confirm confirmDeveloper;

    private Confirm confirmCustomer;

    private LocalDate dateCreated;


    public UUID getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(UUID idMatch) {
        this.idMatch = idMatch;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public Confirm getConfirmDeveloper() {
        return confirmDeveloper;
    }

    public void setConfirmDeveloper(Confirm confirmDeveloper) {
        this.confirmDeveloper = confirmDeveloper;
    }

    public Confirm getConfirmCustomer() {
        return confirmCustomer;
    }

    public void setConfirmCustomer(Confirm confirmCustomer) {
        this.confirmCustomer = confirmCustomer;
    }
}
