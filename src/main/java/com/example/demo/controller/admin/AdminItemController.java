package com.example.demo.controller.admin;

import com.example.demo.constant.RoleKeyEnum;
import com.example.demo.model.request.admin.AdminListRequest;
import com.example.demo.model.request.admin.item.AdminItemRequest;
import com.example.demo.model.request.admin.item.AdminListItemRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/item")
public class AdminItemController {

    private final ItemService itemService;

    @GetMapping
    @Secured({RoleKeyEnum.ADMIN_USER})
    public ResponseEntity<?> list(@ModelAttribute AdminListItemRequest request)
    {
        return ResponseEntity.ok(BaseResponse.of(itemService.list(request)));
    }

    @GetMapping(value = "/{id}")
    @Secured({RoleKeyEnum.ADMIN_USER})
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        return ResponseEntity.ok(BaseResponse.of(itemService.show(id)));
    }


    @GetMapping(value = "bid-history/{id}")
    @Secured({RoleKeyEnum.ADMIN_USER})
    public ResponseEntity<?> listBidHistory(@PathVariable Long id, @ModelAttribute AdminListRequest request)
    {
        return ResponseEntity.ok(BaseResponse.of(itemService.itemBidHistory(id, request)));
    }

    @PostMapping
    @Secured({RoleKeyEnum.ADMIN_USER})
    public ResponseEntity<?> create(@Valid @RequestBody AdminItemRequest request)
    {
        return ResponseEntity.ok(BaseResponse.of(itemService.adminCreateItem(request)));
    }

}
