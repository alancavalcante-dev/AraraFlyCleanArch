package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.infrastructure.security.CreateUserDto;
import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;
import br.com.alanpcavalcante.araraflyapi.domain.user.*;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    private final User user;
    private final Address address;
    private final Profile profile;

    public AuthMapper(User user, Address address, Profile profile) {
        this.user = user;
        this.address = address;
        this.profile = profile;
    }

    public User toUserDomain(CreateUserDto dto) {
        user.setLogin(new Login(dto.login()));
        user.setPassword(new Password(dto.password()));
        user.setIsDeveloper(dto.isDeveloper());

        profile.setName(new Name(dto.name()));
        profile.setCpf(new Cpf(dto.cpf()));
        profile.setEmail(new Email(dto.email()));
        profile.setPhone(new Phone(dto.phone()));

        address.setStreet(dto.street());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setNumber(dto.number());

        profile.setAddress(address);
        user.setProfile(profile);
        return user;
    }



}
