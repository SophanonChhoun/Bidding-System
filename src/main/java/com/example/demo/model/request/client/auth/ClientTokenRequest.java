package com.example.demo.model.request.client.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ClientTokenRequest {

    @NotEmpty
    private String token;

}
