package br.com.alanpcavalcante.araraflyapi.domain.user;

public class Phone {

    private final String phone;

    public Phone(String phone) {
        if (!phone.matches("^\\(?\\d{2}\\)?\\s?(9?\\d{4})-?\\d{4}$")) {
            throw new IllegalArgumentException("Telefone incorreto!");
        }
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


}
