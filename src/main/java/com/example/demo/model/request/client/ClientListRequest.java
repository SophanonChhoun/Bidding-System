package com.example.demo.model.request.client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientListRequest {

    private int size = 10;
    private int pageNumber = 0;
    private String sortProperty = "id";
    private String sortDirection = "DESC";

    private String search;


}
