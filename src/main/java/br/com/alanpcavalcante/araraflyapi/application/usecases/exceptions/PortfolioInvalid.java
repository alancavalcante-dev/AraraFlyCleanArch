package br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions;

public class PortfolioInvalid extends RuntimeException {
    public PortfolioInvalid(String message) {
        super(message);
    }
}
