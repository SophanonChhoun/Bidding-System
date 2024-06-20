package com.example.demo.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google", ignoreUnknownFields = true)
@Getter
@Setter
@RequiredArgsConstructor
public class OAuth2Properties implements Serializable {

    private String clientId;
    private String clientSecret;
    private String redirectUri;

}
