package com.example.demo.utils;

import com.example.demo.model.response.admin.auth.AdminUserProfileResponse;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class AuthProvider {

    public AdminUserProfileResponse getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return (AdminUserProfileResponse) auth.getPrincipal();
        }
        return null;
    }

}
