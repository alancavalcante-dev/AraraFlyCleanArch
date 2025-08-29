package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class CpfOrEmailExists extends RuntimeException {
    public CpfOrEmailExists(String message) {
        super(message);
    }
}
