package com.example.demo.persistence.entity;


import com.example.demo.utils.CoreBase;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = '0'")
@Table(name = "users", indexes = {
        @Index(name = "idx_user_id", columnList = "id"), @Index(name = "idx_user_username", columnList = "username")
})
public class JpaUser extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "is_enable")
    private Character isEnable;

    public void setPassword(String password) {
        this.password = CoreBase.encryptPassword(password);
    }
}