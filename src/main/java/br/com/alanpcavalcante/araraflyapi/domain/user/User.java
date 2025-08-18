package br.com.alanpcavalcante.araraflyapi.domain.user;



import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;

import java.util.UUID;

public class User {

    private UUID id;
    private Login login;
    private Password password;
    private Boolean isDeveloper;
    private UserRole role;
    private Profile profile;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Boolean getIsDeveloper() {
        return isDeveloper;
    }

    public void setIsDeveloper(Boolean developer) {
        isDeveloper = developer;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}