package br.com.alanpcavalcante.araraflyapi.domain.user;

public class Login {

    private String login;

    public Login(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login.isBlank() || login.length() <= 2) {
            throw new IllegalArgumentException("Usuário de login muito curto, minímo é 2 caracteres");
        }
        this.login = login;
    }
}
