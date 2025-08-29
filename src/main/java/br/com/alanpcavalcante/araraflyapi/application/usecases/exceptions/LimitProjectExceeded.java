package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class LimitProjectExceeded extends RuntimeException {
    public LimitProjectExceeded(String message) {
        super(message);
    }
}
