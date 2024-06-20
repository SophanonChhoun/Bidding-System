package com.example.demo.model.response.client.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ClientTokenResponse {

    private String accessToken;
    private String refreshToken;

    private ClientUserProfileResponse user;

}
