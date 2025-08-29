package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class ProjectNotFound extends RuntimeException {
    public ProjectNotFound(String message) {
        super(message);
    }
}
