package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.JpaItemBid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaItemBidRepository extends JpaRepository<JpaItemBid, Long> {

    Optional<JpaItemBid> findTopByOrderByBidAmountDesc();

    Page<JpaItemBid> findAllByItemId(Long itemId, Pageable pageable);
    List<JpaItemBid> findAllByItemIdIn(List<Long> itemIds);

    @Query("SELECT jpa FROM JpaItemBid jpa JOIN jpa.item item " +
            "WHERE (jpa.userId = :userId) " +
            "AND (:status is null or jpa.status = :status)")
    Page<JpaItemBid> findAllByItemNameAndStatus(@Param("status") Character status, @Param("userId") Long userId, Pageable pageable);
}
