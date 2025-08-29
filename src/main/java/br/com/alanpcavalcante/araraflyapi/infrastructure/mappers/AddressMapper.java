package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.user.Address;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.AddressEntity;

public class AddressMapper {

    private final Address address;

    public AddressMapper(Address address) {
        this.address = address;
    }

    public Address entityToDomain(AddressEntity entity) {
        address.setState(entity.getStreet());
        address.setCity(entity.getCity());
        address.setState(entity.getState());
        address.setNumber(entity.getAddressNumber());
        address.setIdAddress(entity.getIdAddress());
        return address;
    }

    public AddressEntity domainToEntity(Address domain) {
        AddressEntity entity = new AddressEntity();
        entity.setIdAddress(domain.getIdAddress());
        entity.setStreet(domain.getStreet());
        entity.setCity(domain.getCity());
        entity.setState(domain.getState());
        entity.setAddressNumber(domain.getNumber());
        return entity;
    }

}
