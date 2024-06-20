package com.example.demo.service;

import com.example.demo.model.request.admin.auth.AdminUserLoginRequest;
import com.example.demo.model.request.client.auth.ClientTokenRequest;
import com.example.demo.model.response.Result;
import com.example.demo.persistence.entity.JpaUser;

public interface AuthService {

    Result<Object> adminLogin(AdminUserLoginRequest request);

    JpaUser adminVerifyToken(String token);


    Result<Object> clientRegister(ClientTokenRequest request);
    Result<Object> clientLogin(ClientTokenRequest request);

}
