package com.example.demo.mapper;

import com.example.demo.component.MapStructLogger;
import com.example.demo.model.request.admin.item.AdminItemRequest;
import com.example.demo.model.response.admin.item.ItemBidResponse;
import com.example.demo.model.response.admin.item.ItemResponse;
import com.example.demo.model.response.client.item.ClientItemBidHistoryResponse;
import com.example.demo.model.response.client.item.ClientItemResponse;
import com.example.demo.persistence.entity.JpaItem;
import com.example.demo.persistence.entity.JpaItemBid;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,  uses = {MapStructLogger.class})
public interface ItemMapper {

    default List<ItemResponse> mapDtoList(List<JpaItem> items) {
        return items.stream().map(this::mapDto).collect(Collectors.toList());
    }

    default ItemBidResponse mapDto(JpaItemBid itemBid) {
        return ItemBidResponse.builder()
                .id(itemBid.getId())
                .bidAmount(itemBid.getBidAmount())
                .status(itemBid.getStatus())
                .userName(itemBid.getUser() != null ? itemBid.getUser().getName() : "")
                .build();
    }


    default ClientItemBidHistoryResponse mapClientDto(JpaItemBid itemBid)
    {
        return ClientItemBidHistoryResponse.builder()
                .id(itemBid.getId())
                .bidAmount(itemBid.getBidAmount())
                .status(itemBid.getStatus())
                .itemName(itemBid.getItem() != null ? itemBid.getItem().getName() : "")
                .createdAt(itemBid.getCreatedAt()).build();
    }

    default ClientItemResponse mapClientDto(JpaItem item)
    {
        return ClientItemResponse.builder()
                .id(item.getId())
                .description(item.getDescription())
                .startPrice(item.getStartPrice())
                .startPrice(item.getStartPrice())
                .endTime(item.getEndTime())
                .build();
    }


    default ItemResponse mapDto(JpaItem item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .startPrice(item.getStartPrice())
                .startTime(item.getStartTime())
                .endTime(item.getEndTime())
                .finished(item.getFinished()).build();
    };

}
