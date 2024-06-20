package com.example.demo.service;

import com.example.demo.model.request.admin.AdminListRequest;
import com.example.demo.model.request.admin.item.AdminItemRequest;
import com.example.demo.model.request.admin.item.AdminListItemRequest;
import com.example.demo.model.request.client.ClientListRequest;
import com.example.demo.model.request.client.item.ClientBidItemRequest;
import com.example.demo.model.request.client.item.ClientListBidItemHistoryRequest;
import com.example.demo.model.response.Result;

public interface ItemService {


    Result<Object> list(AdminListItemRequest request);

    Result<Object> show(Long id);

    Result<Object> adminCreateItem(AdminItemRequest request);

    Result<Object> clientBidItem(ClientBidItemRequest request);

    Result<Object> itemBidHistory(Long id, AdminListRequest request);

    Result<Object> clientListItemBidHistory(ClientListBidItemHistoryRequest request);

    Result<Object> clientListItem(ClientListRequest request);

    void updateWinner();
}
