package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class UsernameExists extends RuntimeException {
    public UsernameExists(){}

    public UsernameExists(String message) {
        super(message);
    }
}
