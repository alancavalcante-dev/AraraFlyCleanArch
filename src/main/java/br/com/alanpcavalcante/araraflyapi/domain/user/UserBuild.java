package br.com.alanpcavalcante.araraflyapi.domain.user;

public class UserBuild {

    private final User user;

    public UserBuild(User user) {
        this.user = user;
    }


    public UserBuild createLogin(Login login, Password password) {
        user.setLogin(login);
        user.setPassword(password);
        return this;
    }

    public UserBuild createProfile(Name name, Cpf cpf, Email email, Phone phone) {
        user.setName(name);
        user.setCpf(cpf);
        user.setEmail(email);
        user.setPhone(phone);
        return this;
    }

    public UserBuild implementAddress(String street, String city, String state, String number) {
        user.setAddress(new Address(street, city, state, number));
        return this;
    }

    public User build() {
        return user;
    }

}
