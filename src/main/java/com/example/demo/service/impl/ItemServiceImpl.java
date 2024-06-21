package com.example.demo.service.impl;

import com.example.demo.constant.*;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.request.admin.AdminListRequest;
import com.example.demo.model.request.admin.item.AdminItemRequest;
import com.example.demo.model.request.admin.item.AdminListItemRequest;
import com.example.demo.model.request.client.ClientListRequest;
import com.example.demo.model.request.client.item.ClientBidItemRequest;
import com.example.demo.model.request.client.item.ClientListBidItemHistoryRequest;
import com.example.demo.model.response.PageResponse;
import com.example.demo.model.response.Result;
import com.example.demo.model.response.admin.item.ItemBidResponse;
import com.example.demo.persistence.entity.JpaItem;
import com.example.demo.persistence.entity.JpaItemBid;
import com.example.demo.persistence.entity.JpaUser;
import com.example.demo.persistence.repository.JpaItemBidRepository;
import com.example.demo.persistence.repository.JpaItemRepository;
import com.example.demo.persistence.repository.JpaUserRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.ItemService;
import com.example.demo.utils.CoreBase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final JpaItemRepository jpaItemRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaItemBidRepository jpaItemBidRepository;
    private final ItemMapper itemMapper;
    private final EmailService emailService;

    @Override
    public Result<Object> list(AdminListItemRequest request) {

        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortProperty());
        Pageable page = PageRequest.of(request.getPageNumber(), request.getSize(), sort);
        Page<JpaItem> items = jpaItemRepository.list(request.getSearch(), request.getFinished(), CoreBase.authProvider.getAuth().getId(), page);

        return Result.of(new PageResponse(itemMapper.mapDtoList(items.getContent()), items));
    }

    @Override
    public Result<Object> show(Long id) {
        return Result.of(itemMapper.mapDto(jpaItemRepository.findTopById(id)
                .orElseThrow(() -> new BusinessException(
                        String.valueOf(InvalidKey.ID),
                        MessageKey.NOT_FOUND,
                        "item not found."
                ))));
    }

    @Override
    public Result<Object> adminCreateItem(AdminItemRequest request) {
        var item = JpaItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startPrice(request.getStartPrice())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .finished(ItemEnum.NOT_FINISHED)
                .user(jpaUserRepository.findTopById(CoreBase.authProvider.getAuth().getId()).orElseThrow(() ->
                        new BusinessException(String.valueOf(InvalidKey.ID), MessageKey.NOT_FOUND, "user not found.")))
                .build();
        item.setCreatedBy(CoreBase.authProvider.getAuth().getUsername());
        item.setIsDeleted(CommonKeyEnum.NOT_DELETED);
        jpaItemRepository.save(item);
        return Result.of(itemMapper.mapDto(item));
    }

    @Override
    public Result<Object> clientBidItem(ClientBidItemRequest request) {

        var item = jpaItemRepository.findTopById(request.getItemId()).orElseThrow(() -> new BusinessException(String.valueOf(InvalidKey.ID), MessageKey.NOT_FOUND,  "item not found."));
        var user = jpaUserRepository.findTopById(CoreBase.authProvider.getAuth().getId()).orElseThrow(() -> new BusinessException(String.valueOf(InvalidKey.ID), MessageKey.NOT_FOUND,  "user not found."));

        if (request.getBidAmount() < item.getStartPrice())
        {
            throw new BusinessException(String.valueOf(InvalidKey.BID_AMOUNT),
                    MessageKey.BID_AMOUNT_NOT_ENOUGH,
                    MessageKey.BID_AMOUNT_NOT_ENOUGH.getContent());
        }

        var biggestBidAmountItemBid = jpaItemBidRepository.findTopByOrderByBidAmountDesc().orElse(null);
        if (biggestBidAmountItemBid != null)
        {
            if (request.getBidAmount() <= biggestBidAmountItemBid.getBidAmount())
            {
                throw new BusinessException(String.valueOf(InvalidKey.BID_AMOUNT),
                        MessageKey.BID_AMOUNT_NOT_ENOUGH,
                        MessageKey.BID_AMOUNT_NOT_ENOUGH.getContent());
            }
        }

        var itemBid = JpaItemBid.builder().item(item).user(user)
                .bidAmount(request.getBidAmount())
                .status(ItemBidEnum.PENDING).build();
        itemBid.setCreatedBy(CoreBase.authProvider.getAuth().getName());
        itemBid.setIsDeleted(CommonKeyEnum.NOT_DELETED);
        jpaItemBidRepository.save(itemBid);
        return Result.of(null);
    }

    @Override
    public Result<Object> itemBidHistory(Long id, AdminListRequest request) {
        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), "bidAmount");
        Pageable page = PageRequest.of(request.getPageNumber(), request.getSize(), sort);
        Page<JpaItemBid> items = jpaItemBidRepository.findAllByItemId(id, page);
        List<ItemBidResponse> responses = new ArrayList<>();
        List<JpaUser> users = jpaUserRepository.findAllById(items.getContent().stream().map(JpaItemBid::getUserId).collect(Collectors.toList()));
        items.getContent().forEach(itemBid -> {
            itemBid.setUser(users.stream().filter(user -> user.getId().equals(itemBid.getUserId())).findFirst().orElse(null));
            responses.add(itemMapper.mapDto(itemBid));
        });

        return Result.of(new PageResponse<>(responses, items));
    }

    @Override
    public Result<Object> clientListItemBidHistory(ClientListBidItemHistoryRequest request) {
        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortProperty());
        Pageable page = PageRequest.of(request.getPageNumber(), request.getSize(), sort);
        Page<JpaItemBid> itemBids = jpaItemBidRepository.findAllByItemNameAndStatus(request.getStatus(), CoreBase.authProvider.getAuth().getId(), page);
        return Result.of(new PageResponse<>(itemBids
                .getContent()
                .stream()
                .map(itemMapper::mapClientDto)
                .collect(Collectors.toList()), itemBids));
    }

    @Override
    public Result<Object> clientListItem(ClientListRequest request) {
        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortProperty());
        Pageable page = PageRequest.of(request.getPageNumber(), request.getSize(), sort);
        Page<JpaItem> items = jpaItemRepository.clientList(ItemEnum.NOT_FINISHED, request.getSearch(), page);
        return Result.of(new PageResponse<>(items
                .getContent()
                .stream()
                .map(itemMapper::mapDto)
                .collect(Collectors.toList()), items));
    }

    @Override
    public void updateWinner() {
        List<JpaItem> items = jpaItemRepository.findAllByEndTimeBeforeAndFinished(LocalDateTime.now(), ItemEnum.NOT_FINISHED);
        List<JpaItemBid> itemBids = jpaItemBidRepository.findAllByItemIdIn(items.stream().map(JpaItem::getId).collect(Collectors.toList()));
        List<JpaUser> users = jpaUserRepository.findAllById(itemBids.stream().map(JpaItemBid::getId).collect(Collectors.toList()));
        items.forEach(item -> {
            item.setFinished(ItemEnum.FINISHED);
            List<JpaItemBid> currentItemBid = itemBids.stream().filter(itemBid -> itemBid.getItemId().equals(item.getId())).collect(Collectors.toList());
            var highestBid = currentItemBid.stream()
                    .max(Comparator.comparingDouble(JpaItemBid::getBidAmount)).orElse(null);
            if (highestBid != null)
            {
                highestBid.setStatus(ItemBidEnum.WIN);
                var remainingBids = currentItemBid.stream().filter(itemBid -> !itemBid.getId().equals(highestBid.getId())).map(itemBid -> {
                    itemBid.setStatus(ItemBidEnum.LOSE);
                    return itemBid;
                }).collect(Collectors.toList());
                jpaItemBidRepository.save(highestBid);
                jpaItemBidRepository.saveAll(remainingBids);
                var winner = users.stream().filter(user -> user.getId().equals(highestBid.getUserId())).findFirst().orElse(null);
                if (winner != null)
                {
                    emailService.sendMail(winner.getEmail(), item.getName());
                }
            }
            jpaItemRepository.save(item);
        });
    }


}
