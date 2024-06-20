package com.example.demo.controller.client;


import com.example.demo.model.request.client.auth.ClientTokenRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientAuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ClientTokenRequest request) {
        return ResponseEntity.ok(BaseResponse.of(authService.clientRegister(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody ClientTokenRequest request) {
        return ResponseEntity.ok(BaseResponse.of(authService.clientLogin(request)));
    }

}
