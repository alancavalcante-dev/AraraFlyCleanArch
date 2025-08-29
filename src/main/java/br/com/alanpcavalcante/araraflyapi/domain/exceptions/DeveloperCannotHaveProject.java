package br.com.alanpcavalcante.araraflyapi.domain.exceptions;

public class DeveloperCannotHaveProject extends RuntimeException {
    public DeveloperCannotHaveProject(String message) {
        super(message);
    }
}
