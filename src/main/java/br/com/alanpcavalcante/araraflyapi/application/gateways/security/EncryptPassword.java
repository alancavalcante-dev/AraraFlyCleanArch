package br.com.alanpcavalcante.araraflyapi.application.gateways.security;

import br.com.alanpcavalcante.araraflyapi.domain.user.Password;

public interface EncryptPassword {

    Password encode(Password password);
}
