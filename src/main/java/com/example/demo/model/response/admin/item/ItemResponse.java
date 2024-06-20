package com.example.demo.model.response.admin.item;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class ItemResponse {

    private Long id;
    private String name;
    private String description;
    private Double startPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Character finished;

}
