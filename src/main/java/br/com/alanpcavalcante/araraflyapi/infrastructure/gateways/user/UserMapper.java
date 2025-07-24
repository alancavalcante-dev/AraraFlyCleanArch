package br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user;

import br.com.alanpcavalcante.araraflyapi.domain.user.*;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user.AddressEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user.UserEntity;

public class UserMapper {

    private final UserBuild userBuild;

    public UserMapper(UserBuild userBuild) {
        this.userBuild = userBuild;
    }

    public User entityToDomain(UserEntity entity) {
        AddressEntity address = entity.getAddressEntity();
        return userBuild
                .createLogin(new Login(entity.getLogin()), new Password(entity.getPassword()))
                .createProfile(
                        new Name(entity.getName()),
                        new Cpf(entity.getCpf()),
                        new Email(entity.getEmail()),
                        new Phone(entity.getPhone())

                )
                .implementAddress(
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getNumber())
                .build();
    }

    public UserEntity domainToEntity(User user) {
        AddressEntity address = new AddressEntity();
        address.setStreet(user.getAddress().getStreet());
        address.setState(user.getAddress().getState());
        address.setCity(user.getAddress().getCity());
        address.setNumber(user.getAddress().getNumber());

        UserEntity entity = new UserEntity();

        entity.setName(user.getName().getName());
        entity.setCpf(user.getCpf().getCpf());
        entity.setEmail(user.getEmail().getEmail());
        entity.setPhone(user.getPhone().getPhone());
        entity.setLogin(user.getLogin().getLogin());
        entity.setPassword(user.getPassword().getPassword());
        entity.setAddressEntity(address);
        return entity;
    }

}
