package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class UserDoesNotBelong extends RuntimeException {
    public UserDoesNotBelong(String message) {
        super(message);
    }
}
