package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class ProjectNotStating extends RuntimeException {
    public ProjectNotStating(String message) {
        super(message);
    }
}
