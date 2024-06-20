package com.example.demo.model.response.admin.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AdminUserLoginResponse {

    private String accessToken;
    private String refreshToken;
    private AdminUserProfileResponse user;
}
