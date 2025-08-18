package br.com.alanpcavalcante.araraflyapi.domain.pointmarking;

import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class PointMarking {

    private UUID idPointMarking;

    private Project project;

    private String comment;

    private String commentsGeneratedIA;

    private LocalDateTime dateStarting;

    private LocalDateTime dateClosing;

    private LocalDateTime dateCreated;

    private User developer;


    public PointMarking() {}


    public UUID getIdPointMarking() {
        return idPointMarking;
    }

    public void setIdPointMarking(UUID idPointMarking) {
        this.idPointMarking = idPointMarking;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentsGeneratedIA() {
        return commentsGeneratedIA;
    }

    public void setCommentsGeneratedIA(String commentsGeneratedIA) {
        this.commentsGeneratedIA = commentsGeneratedIA;
    }

    public LocalDateTime getDateStarting() {
        return dateStarting;
    }

    public void setDateStarting(LocalDateTime dateStarting) {
        this.dateStarting = dateStarting;
    }

    public LocalDateTime getDateClosing() {
        return dateClosing;
    }

    public void setDateClosing(LocalDateTime dateClosing) {
        this.dateClosing = dateClosing;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        if (LocalDateTime.now().isAfter(dateCreated)) {
            throw new IllegalArgumentException("Data criação é maios do que a data atual");
        }
        this.dateCreated = dateCreated;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public User getCustomer() {
        return this.project.getUser();
    }

}
