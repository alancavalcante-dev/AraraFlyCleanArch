package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.profile;

import br.com.alanpcavalcante.araraflyapi.application.gateways.profile.ProfileRepository;
import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;
import br.com.alanpcavalcante.araraflyapi.domain.user.Cpf;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProfileRepositoryImpl implements ProfileRepository {

    @Autowired
    private ProfileRepositoryJpa profileRepositoryJpa;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public Optional<Profile> getProfileByEmailOrCpf(Email email, Cpf cpf) {
        return profileRepositoryJpa.findProfileByEmailOrCpf(email.getEmail(), cpf.getCpf())
                .map(profileMapper::entityToDomain);
    }
}
