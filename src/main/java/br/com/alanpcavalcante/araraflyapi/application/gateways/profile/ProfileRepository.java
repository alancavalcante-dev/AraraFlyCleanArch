package br.com.alanpcavalcante.araraflyapi.application.gateways.profile;

import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;
import br.com.alanpcavalcante.araraflyapi.domain.user.Cpf;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;


import java.util.Optional;

public interface ProfileRepository {

    Optional<Profile> getProfileByEmailOrCpf(Email email, Cpf cpf);

}
