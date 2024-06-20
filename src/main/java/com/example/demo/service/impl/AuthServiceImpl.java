package com.example.demo.service.impl;

import com.example.demo.constant.*;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.request.admin.auth.AdminUserLoginRequest;
import com.example.demo.model.request.client.auth.ClientTokenRequest;
import com.example.demo.model.response.Result;
import com.example.demo.model.response.admin.auth.AdminUserLoginResponse;
import com.example.demo.model.response.client.auth.ClientTokenResponse;
import com.example.demo.persistence.entity.JpaUser;
import com.example.demo.persistence.entity.JpaUserLoginAccess;
import com.example.demo.persistence.repository.JpaUserLoginAccessRepository;
import com.example.demo.persistence.repository.JpaUserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;
import com.example.demo.service.client.GoogleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserLoginAccessRepository jpaUserLoginAccessRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final GoogleClient googleClient;

    @Override
    public Result<Object> adminLogin(AdminUserLoginRequest request) {

        var userInfo = googleClient.getInfo(request.getToken());
        var user = jpaUserRepository.findTopByEmail(userInfo.getEmail()).orElseThrow(() -> new BusinessException(String.valueOf(InvalidKey.USER_NAME), MessageKey.NOT_FOUND, "user not found."));
        if (!user.getIsEnable().equals(UserEnum.active) || !user.getUserType().equals(String.valueOf(UserTypeEnum.ADMIN_USER)))
        {
            throw new BusinessException(String.valueOf(InvalidKey.IS_ENABLE), MessageKey.USER_NOT_ACTIVE, "user not active");
        }

        var token = this.generateAccessToken(user);

        return Result.of(AdminUserLoginResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .user(userMapper.mapUser(user)).build());
    }

    @Override
    public JpaUser adminVerifyToken(String token) {
        var jpaUserLoginAccess = jpaUserLoginAccessRepository.findTopByAccessToken(token).orElse(null);
        if (jpaUserLoginAccess != null)
        {
            if (jpaUserLoginAccess.getAccessTokenExpiredAt().isAfter(LocalDateTime.now()) && !jpaUserLoginAccess.getIsRevoke().equals(UserEnum.revokeActive))
            {
                return jpaUserRepository.findTopById(jpaUserLoginAccess.getUserId()).orElse(null);
            }
        }
        return null;
    }

    @Override
    public Result<Object> clientRegister(ClientTokenRequest request) {

        var userInfo = googleClient.getInfo(request.getToken());
        var user = jpaUserRepository.findTopByEmail(userInfo.getEmail()).orElse(null);
        if (user != null)
        {
            throw new BusinessException(String.valueOf(InvalidKey.EMAIL), MessageKey.EMAIL_ALREADY_EXIST, "user already exist.");
        }
        user = userMapper.mapToEntity(userInfo);
        user.setCreatedBy("system");
        user.setIsDeleted(CommonKeyEnum.NOT_DELETED);
        user.setIsEnable(UserEnum.active);
        jpaUserRepository.save(user);
        var token = this.generateAccessToken(user);

        return Result.of(ClientTokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .user(userMapper.mapToClientDto(user))
                .build());
    }

    @Override
    public Result<Object> clientLogin(ClientTokenRequest request) {
        var userInfo = googleClient.getInfo(request.getToken());
        var user = jpaUserRepository.findTopByEmail(userInfo.getEmail()).orElseThrow(() -> new BusinessException(String.valueOf(InvalidKey.EMAIL), MessageKey.NOT_FOUND, "user not exist"));
        if (!user.getIsEnable().equals(UserEnum.active) || !user.getUserType().equals(String.valueOf(UserTypeEnum.CLIENT_USER)))
        {
            throw new BusinessException(String.valueOf(InvalidKey.IS_ENABLE), MessageKey.USER_NOT_ACTIVE, "user not active");
        }
        var token = this.generateAccessToken(user);

        return Result.of(ClientTokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .user(userMapper.mapToClientDto(user))
                .build());
    }


    private JpaUserLoginAccess generateAccessToken(JpaUser user)
    {
        jpaUserLoginAccessRepository.updateRevokeUser(user.getId(), UserEnum.revokeActive);
        var loginAccessToken = JpaUserLoginAccess.builder()
                .userId(user.getId())
                .accessToken(jwtService.generateToken(user, UserEnum.accessTokenExpiredTime))
                .refreshToken(jwtService.generateToken(user, UserEnum.refreshTokenExpiredTime))
                .accessTokenExpiredAt(LocalDateTime.now().plusMinutes(UserEnum.accessTokenExpiredTime))
                .refreshTokenExpiredAt(LocalDateTime.now().plusMinutes(UserEnum.refreshTokenExpiredTime))
                .isRevoke(UserEnum.revokeInactive)
                .build();
        jpaUserLoginAccessRepository.save(loginAccessToken);
        return loginAccessToken;
    }
}
