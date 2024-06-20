package com.example.demo.model.response.client.item;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ClientItemResponse {

    private Long id;
    private String name;
    private String description;
    private Double startPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
