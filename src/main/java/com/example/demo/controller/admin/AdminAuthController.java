package com.example.demo.controller.admin;

import com.example.demo.model.request.admin.auth.AdminUserLoginRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminAuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminUserLoginRequest request)
    {
        return ResponseEntity.ok(BaseResponse.of(authService.adminLogin(request)));
    }

}
