package com.example.demo.model.request.client.item;

import com.example.demo.model.request.client.ClientListRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientListBidItemHistoryRequest extends ClientListRequest {

    private Character status;

}
