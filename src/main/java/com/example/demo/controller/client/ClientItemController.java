package com.example.demo.controller.client;

import com.example.demo.constant.RoleKeyEnum;
import com.example.demo.model.request.client.ClientListRequest;
import com.example.demo.model.request.client.item.ClientBidItemRequest;
import com.example.demo.model.request.client.item.ClientListBidItemHistoryRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client/item")
public class ClientItemController {

    private final ItemService itemService;

    @PostMapping
    @Secured({RoleKeyEnum.NORMAL_USER})
    public ResponseEntity<?> create(@Valid @RequestBody ClientBidItemRequest request)
    {
        return ResponseEntity.ok(BaseResponse.of(itemService.clientBidItem(request)));
    }

    @GetMapping()
    @Secured({RoleKeyEnum.NORMAL_USER})
    public ResponseEntity<?> list(@ModelAttribute ClientListRequest request)
    {
        return ResponseEntity.ok(BaseResponse.of(itemService.clientListItem(request)));
    }


    @GetMapping(value = "bid")
    @Secured({RoleKeyEnum.NORMAL_USER})
    public ResponseEntity<?> listBidHistory(@ModelAttribute ClientListBidItemHistoryRequest request)
    {
        return ResponseEntity.ok(BaseResponse.of(itemService.clientListItemBidHistory(request)));
    }

}
