package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

import br.com.alanpcavalcante.araraflyapi.application.gateways.profile.ProfileRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;
import br.com.alanpcavalcante.araraflyapi.domain.profile.ProfileBuild;
import br.com.alanpcavalcante.araraflyapi.domain.user.*;

import java.util.Optional;

public class CreateUser {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public CreateUser(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public User create(User user, Profile profile) throws Exception {

        Optional<User> userDomainOpt = userRepository.getUserByLoginOrCpfOrEmail(
                user.getLogin(), user.getProfile().getCpf(), user.getProfile().getEmail());

        if (userDomainOpt.isPresent()) throw new Exception("Login, Cpf ou Email já cadastrado");

        Optional<Profile> profileDomainObj = profileRepository.getProfileByEmailOrCpf(profile.getEmail(), profile.getCpf());
        if (profileDomainObj.isPresent()) throw new Exception("Email ou Cpf já cadastrados");

        user.setProfile(profile);
        return userRepository.save(user);
    }

}
