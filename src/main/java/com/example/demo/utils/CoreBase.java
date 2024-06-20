package com.example.demo.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CoreBase {

    private static PasswordEncoder passwordEncoder;
    public static AuthProvider authProvider;


    public CoreBase(PasswordEncoder passwordEncoder, AuthProvider authProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authProvider = authProvider;
    }

    public static String encryptPassword(String password)
    {
        return passwordEncoder.encode(password);
    }

}
