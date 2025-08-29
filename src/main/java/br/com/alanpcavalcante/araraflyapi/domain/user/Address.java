package br.com.alanpcavalcante.araraflyapi.domain.user;

import java.util.UUID;

public class Address {

    private UUID idAddress;
    private String street;
    private String city;
    private String state;
    private Integer number;

    public UUID getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(UUID idAddress) {
        this.idAddress = idAddress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        caracterMin(street);
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        caracterMin(city);
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        caracterMin(state);
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    private void caracterMin(String text) {
        if (text != null && text.length() <= 2) {
            throw new IllegalArgumentException("Tem que ser maior de 2 caracteres");
        }
    }
}
