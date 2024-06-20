package com.example.demo.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PageResponse<D, T> {

    private List<D> list;
    private boolean last;
    private int totalPages;
    private long totalElements;
    private int size;
    private int pageNumber;
    private int row;
    private String sortProperty;
    private String sortDirection;

    public PageResponse(List<D> list, Page<T> page) {
        this.list = list;
        if (page != null) {
            this.last = page.isLast();
            this.totalPages = page.getTotalPages();
            this.totalElements = page.getTotalElements();
            this.size = page.getSize();
            this.pageNumber = page.getPageable().getPageNumber() + 1;
            this.row = page.getContent().size();
            Sort sort = page.getSort();
            if (sort != null) {
                for (Sort.Order order : sort) {
                    this.sortProperty = order.getProperty();
                    this.sortDirection = order.getDirection().isDescending() ? Sort.Direction.DESC.name() : Sort.Direction.ASC.name();
                    break; // Assuming only one sort property for simplicity
                }
            }
        } else {
            // Initialize default values or handle the case where page is null
            this.last = false;
            this.totalPages = 0;
            this.totalElements = 0;
            this.size = 0;
            this.pageNumber = 0;
            this.row = 0;
        }
    }
}
