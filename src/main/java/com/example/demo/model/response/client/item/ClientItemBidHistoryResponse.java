package com.example.demo.model.response.client.item;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ClientItemBidHistoryResponse {

    private Long id;
    private String itemName;
    private Double bidAmount;
    private Character status;
    private Timestamp createdAt;

}
