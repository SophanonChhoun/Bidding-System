package com.example.demo.service;

import com.example.demo.persistence.entity.JpaUser;

public interface JwtService {

    String generateToken(JpaUser user, Long expiredTime);

    Boolean verifyToken(String token);

}
