package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.constant.RoleKeyEnum;
import com.example.demo.constant.UserTypeEnum;
import com.example.demo.model.response.admin.auth.AdminUserProfileResponse;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("/auth/google")){
            filterChain.doFilter(request, response);
            return;
        }
        String tokenHeader = request.getHeader("Authentication");
        if (tokenHeader != null && tokenHeader.split(" ").length > 1) {
            String token = tokenHeader.replace("Bearer ", "");
            if (jwtService.verifyToken(token))
            {
                var user = authService.adminVerifyToken(token);
                if (user != null)
                {
                    AdminUserProfileResponse profile = null;
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    if (request.getServletPath().contains("api/v1/admin") && user.getUserType().equals(String.valueOf(UserTypeEnum.ADMIN_USER)))
                    {
                        profile = AdminUserProfileResponse.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .name(user.getName())
                                .build();
                        authorities.add(new SimpleGrantedAuthority(RoleKeyEnum.ADMIN_USER));
                    }else {
                        profile = AdminUserProfileResponse.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .name(user.getName())
                                .build();
                        authorities.add(new SimpleGrantedAuthority(RoleKeyEnum.NORMAL_USER));
                    }
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(profile, null, authorities);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);

    }
}
