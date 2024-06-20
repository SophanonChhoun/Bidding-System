package com.example.demo.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = true)
@Getter
@Setter
@RequiredArgsConstructor
public class AppProperties {

    private String sampleUserSocialToken;
    private String secretKey;
    private String issuer;


}
