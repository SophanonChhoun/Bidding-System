package com.example.demo.model.response.admin.item;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ItemBidResponse {

    private Long id;
    private String userName;
    private Double bidAmount;
    private Character status;
}
