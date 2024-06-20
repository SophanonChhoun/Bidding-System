package com.example.demo.mapper;

import com.example.demo.constant.UserTypeEnum;
import com.example.demo.model.clientresponse.auth.ClientGoogleTokenResponse;
import com.example.demo.model.response.admin.auth.AdminUserProfileResponse;
import com.example.demo.model.response.client.auth.ClientUserProfileResponse;
import com.example.demo.persistence.entity.JpaUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Random;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    default JpaUser mapToEntity(ClientGoogleTokenResponse response)
    {
        return JpaUser.builder()
                .email(response.getEmail())
                .userType(String.valueOf(UserTypeEnum.CLIENT_USER))
                .name(response.getName())
                .password(UUID.randomUUID().toString())
                .username(response.getName()).build();
    }

    default AdminUserProfileResponse mapUser(JpaUser user) {
        return AdminUserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername()).build();
    };

    default ClientUserProfileResponse mapToClientDto(JpaUser user) {
        return ClientUserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername()).build();
    };

}
