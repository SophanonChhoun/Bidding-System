package com.example.demo.model.response.client.auth;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ClientUserProfileResponse {

    private Long id;
    private String name;
    private String username;

}
