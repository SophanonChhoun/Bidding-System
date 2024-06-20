package com.example.demo.persistence.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_login_accesses", indexes = {
        @Index(name = "idx_user_login_access_access_token", columnList = "access_token"),
        @Index(name = "idx_user_login_access_refresh_token", columnList = "refresh_token"),
})
public class JpaUserLoginAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "access_token_expired_at", nullable = false)
    private LocalDateTime accessTokenExpiredAt;

    @Column(name = "refresh_token_expired_at", nullable = false)
    private LocalDateTime refreshTokenExpiredAt;

    @Column(name = "is_revoke")
    private Character isRevoke;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
