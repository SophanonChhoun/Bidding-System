package com.example.demo.model.request.admin.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class AdminUserLoginRequest {

   @NotEmpty
   private String token;

}
