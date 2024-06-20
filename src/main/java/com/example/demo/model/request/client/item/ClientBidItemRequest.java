package com.example.demo.model.request.client.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ClientBidItemRequest {

    @NotNull
    private Double bidAmount;

    @NotNull
    private Long itemId;
}