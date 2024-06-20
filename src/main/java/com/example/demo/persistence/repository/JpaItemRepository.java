package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.JpaItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaItemRepository extends JpaRepository<JpaItem, Long>, PagingAndSortingRepository<JpaItem, Long> {



    @Query("select jpa from JpaItem jpa " +
            "where (:search is null or lower(jpa.name) like %:search%) " +
            "and (:finished is null or jpa.finished = :finished)" +
            "and jpa.ownerId = :userId")
    Page<JpaItem> list(@Param("search") String search, @Param("finished") Character finished, @Param("userId") Long userId, Pageable pageable);

    Optional<JpaItem> findTopById(Long id);


    @Query("SELECT jpa FROM JpaItem jpa " +
            "WHERE jpa.finished = :finished " +
            "AND (:search is null or LOWER(jpa.name) LIKE %:search%)")
    Page<JpaItem> clientList(@Param("finished") Character finished, @Param("search") String search, Pageable pageable);


    List<JpaItem> findAllByEndTimeBeforeAndFinished(LocalDateTime currentTime, Character finished);
}
