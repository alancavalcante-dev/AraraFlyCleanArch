package br.com.alanpcavalcante.araraflyapi.domain.exceptions;

public class CustomerCannotProgram extends RuntimeException {
    public CustomerCannotProgram(String message) {
        super(message);
    }
}
