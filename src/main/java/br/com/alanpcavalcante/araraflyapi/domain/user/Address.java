package br.com.alanpcavalcante.araraflyapi.domain.user;

public class Address {

    private String street;
    private String city;
    private String state;
    private String number;

    public Address(String street, String city, String state, String number) {
        caracterMin(street);
        caracterMin(city);
        caracterMin(state);
        caracterMin(number);
        this.street = street;
        this.city = city;
        this.state = state;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private void caracterMin(String texto) {
        if (texto.length() <= 2) {
            throw new IllegalArgumentException("Tem que ser maior de 2 caracteres");
        }
    }
}
