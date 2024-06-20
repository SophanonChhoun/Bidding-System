package com.example.demo.model.response.admin.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AdminUserProfileResponse {

    private Long id;
    private String name;
    private String username;


}
