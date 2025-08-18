package br.com.alanpcavalcante.araraflyapi.domain.profile;

import br.com.alanpcavalcante.araraflyapi.domain.user.*;

import java.nio.file.Path;
import java.util.UUID;

public class Profile {

    private UUID idProfile;
    private Name name;
    private Cpf cpf;
    private Email email;
    private Phone phone;
    private Address address;
    private Path picture;

    public UUID getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(UUID idProfile) {
        this.idProfile = idProfile;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Path getPicture() {
        return picture;
    }

    public void setPicture(Path picture) {
        this.picture = picture;
    }
}
