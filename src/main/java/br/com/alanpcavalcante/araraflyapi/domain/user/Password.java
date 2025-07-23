package br.com.alanpcavalcante.araraflyapi.domain.user;

public class Password {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password.strip();

        if (password.isBlank() || password.length() < 9) {
            throw new IllegalArgumentException("Senha tem que ter no minímo 8 caracteres");
        }

        String especials = "!@#$%¨&*()-_=+[{]}?/;:.,";
        boolean containEspecial = false;

        for (char c : especials.toCharArray()) {
            if (password.contains(String.valueOf(c))) {
                containEspecial = true;
                break;
            }
        }
        if (containEspecial) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 1 caracter especial: !@#$%¨&*()-_=+[{]}?/;:.,");
        }

        String numbers = "0123456789";
        boolean containNumber = false;

        for (char c : numbers.toCharArray()) {
            if (password.contains(String.valueOf(c))) {
                containNumber = true;
                break;
            }
        }
        if (containNumber) {
            throw new IllegalArgumentException("Deve ter pelo menos 1 número");
        }

        this.password = password;
    }
}
