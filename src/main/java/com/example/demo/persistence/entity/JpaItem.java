package com.example.demo.persistence.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Where(clause = "is_deleted = '0'")
@Entity
@FieldNameConstants
@Table(name = "items", indexes = {
        @Index(name = "idx_item_id", columnList = "id"),
        @Index(name = "idx_item_start_time", columnList = "start_time"),
        @Index(name = "idx_item_end_time", columnList = "end_time"),
        @Index(name = "idx_item_owner_id", columnList = "owner_id"),
})
public class JpaItem extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_price", nullable = false)
    private Double startPrice;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "owner_id", nullable = false, insertable = false, updatable = false)
    private Long ownerId;

    @Column(name = "finished", nullable = false)
    private Character finished;


    @OneToOne(targetEntity = JpaUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private JpaUser user;

}
