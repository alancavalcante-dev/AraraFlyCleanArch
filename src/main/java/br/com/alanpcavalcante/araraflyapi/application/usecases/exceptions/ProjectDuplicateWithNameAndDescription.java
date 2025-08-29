package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class ProjectDuplicateWithNameAndDescription extends RuntimeException {
    public ProjectDuplicateWithNameAndDescription(String message) {
        super(message);
    }
}
