package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.persistence.entity.JpaUser;
import com.example.demo.property.AppProperties;
import com.example.demo.service.JwtService;
import com.example.demo.utils.ObjectUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final AppProperties appProperties;
    private long EXPIRATION_TIME = 60 * 1000;

    @Override
    public String generateToken(JpaUser user, Long expiredTime) {
        Algorithm algorithm = Algorithm.HMAC256(appProperties.getSecretKey());

        return JWT.create()
                .withIssuer(appProperties.getIssuer())
                .withSubject(user.getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRATION_TIME * expiredTime)))
                .sign(algorithm);
    }

    @Override
    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(appProperties.getSecretKey());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(appProperties.getIssuer())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
