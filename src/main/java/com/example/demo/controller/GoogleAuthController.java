package com.example.demo.controller;

import com.example.demo.model.response.BaseResponse;
import com.example.demo.model.response.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/google")
public class GoogleAuthController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/user")
    public String getUserDetails(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // Access user details from OAuth2User object
            return "Hello, " + principal.getAttribute("name");
        } else {
            return "User details not found";
        }
    }

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        return ResponseEntity.ok(BaseResponse.of(Result.of("Code token login: " + code)));
    }
}
