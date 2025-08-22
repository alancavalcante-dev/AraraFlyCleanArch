package br.com.alanpcavalcante.araraflyapi.infrastructure.security;

import br.com.alanpcavalcante.araraflyapi.application.gateways.security.EncryptPassword;
import br.com.alanpcavalcante.araraflyapi.domain.user.Password;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPasswordImpl implements EncryptPassword {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EncryptPasswordImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Password encode(Password password) {
        return new Password(bCryptPasswordEncoder.encode(password.getPassword()));
    }
}
