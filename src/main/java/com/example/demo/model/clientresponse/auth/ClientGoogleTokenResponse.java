package com.example.demo.model.clientresponse.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter(AccessLevel.MODULE)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientGoogleTokenResponse {

    @JsonProperty("iss")
    private String issuer;

    @JsonProperty("azp")
    private String authorizedParty;

    @JsonProperty("aud")
    private String audience;

    @JsonProperty("sub")
    private String subject;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_verified")
    private boolean emailVerified;

    @JsonProperty("at_hash")
    private String accessTokenHash;

    @JsonProperty("name")
    private String name;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("iat")
    private long issuedAt;

    @JsonProperty("exp")
    private long expiration;

    @JsonProperty("alg")
    private String algorithm;

    @JsonProperty("kid")
    private String keyId;

    @JsonProperty("typ")
    private String type;
}
