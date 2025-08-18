package br.com.alanpcavalcante.araraflyapi.application.gateways.notification;

public class TextField {

    private String text;

    public TextField(String text) {
        validate(text);
        this.text = text;
    }

    public void validate(String text) {
        if (text.length() < 3) {
            throw new IllegalArgumentException("Os campos tem que ter no minímo 3 caracteres");
        }
    }

    public String getValue() {
        return text;
    }

    public void setValue(String text) {
        this.text = text;
    }
}
