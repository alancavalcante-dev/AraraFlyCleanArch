package br.com.alanpcavalcante.araraflyapi.domain.profile;

import br.com.alanpcavalcante.araraflyapi.domain.user.*;



public class ProfileBuild {

    private Profile profile;


    public ProfileBuild createProfile(Name name, Cpf cpf, Email email, Phone phone) {
        Profile profile = new Profile();
        profile.setName(name);
        profile.setCpf(cpf);
        profile.setEmail(email);
        profile.setPhone(phone);
        this.profile = profile;
        return this;
    }

    public ProfileBuild implementAddress(String street, String city, String state, Integer number) {
        Address address = new Address();
        address.setState(street);
        address.setCity(city);
        address.setState(state);
        address.setNumber(number);
        this.profile.setAddress(address);
        return this;
    }

    public Profile build() {
        return this.profile;
    }

}
