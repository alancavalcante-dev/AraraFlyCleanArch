package br.com.alanpcavalcante.araraflyapi.infrastructure.security;

import br.com.alanpcavalcante.araraflyapi.application.gateways.security.TokenService;
import br.com.alanpcavalcante.araraflyapi.domain.user.UserRole;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {


    @Value("${jwt.secret}")
    private String secret;


    @Override
    public String generateToken(UserEntity user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            UserRole roleString = user.getRole();

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withClaim("name", user.getProfile().getName())
                    .withClaim("picture", user.getProfile() != null ? user.getProfile().getPictureUrl() : null)
                    .withClaim("role", roleString.toString())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    @Override
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }


    @Override
    public String getRoleFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getClaim("role").asString();
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
