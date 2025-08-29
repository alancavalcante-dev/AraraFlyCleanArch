package br.com.alanpcavalcante.araraflyapi.domain.exceptions;

public class TextMinAndMaxCharacters extends RuntimeException {
    public TextMinAndMaxCharacters(String message) {
        super(message);
    }
}
