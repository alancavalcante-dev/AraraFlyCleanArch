package br.com.alanpcavalcante.araraflyapi.domain.profile;

import br.com.alanpcavalcante.araraflyapi.domain.user.*;



public class ProfileBuild {

    private final Profile profile;
    private final Address address;

    public ProfileBuild(Profile profile, Address address) {
        this.profile = profile;
        this.address = address;
    }


    public ProfileBuild createProfile(Name name, Cpf cpf, Email email, Phone phone) {
        profile.setName(name);
        profile.setCpf(cpf);
        profile.setEmail(email);
        profile.setPhone(phone);
        return this;
    }

    public ProfileBuild implementAddress(String street, String city, String state, Integer number) {
        address.setState(street);
        address.setCity(city);
        address.setState(state);
        address.setNumber(number);
        profile.setAddress(address);
        return this;
    }

    public Profile build() {
        return profile;
    }

}
