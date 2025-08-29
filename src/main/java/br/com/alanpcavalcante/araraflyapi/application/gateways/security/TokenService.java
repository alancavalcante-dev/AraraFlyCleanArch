package br.com.alanpcavalcante.araraflyapi.application.gateways.security;

import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;

public interface TokenService {

    String generateToken(UserEntity user);
    String validateToken(String token);
    String getRoleFromToken(String token);

}
