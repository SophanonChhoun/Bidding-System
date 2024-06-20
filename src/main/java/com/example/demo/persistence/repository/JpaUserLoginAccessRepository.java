package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.JpaUserLoginAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface JpaUserLoginAccessRepository extends JpaRepository<JpaUserLoginAccess, Long> {

    Optional<JpaUserLoginAccess> findTopByAccessToken(String accessToken);

    @Modifying
    @Transactional
    @Query("UPDATE JpaUserLoginAccess jpa set jpa.isRevoke = :revoke where jpa.userId = :userId")
    void updateRevokeUser(@Param("userId") Long userId, @Param("revoke") Character revoke);

}
