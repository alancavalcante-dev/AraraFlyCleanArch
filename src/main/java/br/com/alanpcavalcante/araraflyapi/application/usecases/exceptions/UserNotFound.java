package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
