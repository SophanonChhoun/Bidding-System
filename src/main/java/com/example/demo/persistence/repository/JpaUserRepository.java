package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, Long>, PagingAndSortingRepository<JpaUser, Long> {


    Optional<JpaUser> findTopById(Long id);

    Optional<JpaUser> findTopByUsername(String username);

    Optional<JpaUser> findTopByEmail(String email);

}
