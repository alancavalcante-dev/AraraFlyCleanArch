package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;
import br.com.alanpcavalcante.araraflyapi.domain.profile.ProfileBuild;
import br.com.alanpcavalcante.araraflyapi.domain.user.*;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.ProfileEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    @Autowired
    private ProfileBuild profileBuild;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;


    public Profile entityToDomain(ProfileEntity entity) {
        ProfileBuild build = profileBuild.createProfile(
                new Name(entity.getName()),
                new Cpf(entity.getCpf()),
                new Email(entity.getEmail()),
                new Phone(entity.getPhone())
        );
        build.implementAddress(
                entity.getAddress().getStreet(),
                entity.getAddress().getCity(),
                entity.getAddress().getState(),
                entity.getAddress().getAddressNumber()
        );
        Profile profile = build.build();
        profile.setIdProfile(entity.getIdProfile());
        return profile;
    }

    public ProfileEntity domainToEntity(Profile profile) {
        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setName(profile.getName().getName());
        profileEntity.setEmail(profile.getEmail().getEmail());
        profileEntity.setPhone(profile.getPhone().getPhone());
        profileEntity.setCpf(profile.getCpf().getCpf());

        if (profile.getPicture() != null) {
            profileEntity.setPictureUrl(profile.getPicture().toString());
        } else {
            profileEntity.setPictureUrl(null);
        }

        profileEntity.setAddress(addressMapper.domainToEntity(profile.getAddress()));
        return profileEntity;
    }
}
