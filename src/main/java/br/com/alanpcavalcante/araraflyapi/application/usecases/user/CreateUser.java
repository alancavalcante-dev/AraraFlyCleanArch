package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

import br.com.alanpcavalcante.araraflyapi.application.gateways.profile.ProfileRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.security.EncryptPassword;
import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;
import br.com.alanpcavalcante.araraflyapi.domain.user.*;

import java.util.Optional;

public class CreateUser {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EncryptPassword encryptPassword;


    public CreateUser(UserRepository userRepository, ProfileRepository profileRepository, EncryptPassword encryptPassword) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.encryptPassword = encryptPassword;
    }

    public User create(User user) throws Exception {

        Optional<User> userDomainOpt = userRepository.getUserByLogin(user.getLogin());

        if (userDomainOpt.isPresent()) throw new Exception("Username já cadastrado");

        Profile profile = user.getProfile();
        Optional<Profile> profileDomainObj = profileRepository.getProfileByEmailOrCpf(profile.getEmail(), profile.getCpf());
        if (profileDomainObj.isPresent()) throw new Exception("Email ou Cpf já cadastrados");

        user.setProfile(profile);
        user.setPassword(encryptPassword.encode(user.getPassword()));

        if (user.getIsDeveloper()) {
            user.setRole(UserRole.DEVELOPER);
        } else user.setRole(UserRole.CUSTOMER);
        return userRepository.save(user);
    }

}
