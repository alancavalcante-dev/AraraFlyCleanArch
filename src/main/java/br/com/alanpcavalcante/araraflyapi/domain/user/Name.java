package br.com.alanpcavalcante.araraflyapi.domain.user;

public class Name {

    private final String name;

    public Name(String name) {
        if (name == null || name.isBlank() || name.strip().length() <= 2) {
            throw new IllegalArgumentException("O name deve ter mais de 2 caracteres.");
        }
        this.name = name.strip();
    }

    public String getName() {
        return name;
    }
}
