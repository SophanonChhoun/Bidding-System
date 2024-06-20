package com.example.demo.persistence.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Where(clause = "is_deleted = '0'")
@Entity
@FieldNameConstants
@Table(name = "item_bids", indexes = {
        @Index(name = "idx_item_bid_id", columnList = "id"),
        @Index(name = "idx_item_bid_item_id", columnList = "item_id"),
        @Index(name = "idx_item_bid_user_id", columnList = "user_id"),
        @Index(name = "idx_item_bid_status", columnList = "status"),
})
public class JpaItemBid extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;

    @Column(name = "item_id", nullable = false, insertable = false, updatable = false)
    private Long itemId;

    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT '0'")
    private Character status;

    @Column(name = "bid_amount", nullable = false)
    private Double bidAmount;


    @OneToOne(targetEntity = JpaUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private JpaUser user;

    @OneToOne(targetEntity = JpaItem.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    private JpaItem item;
}
