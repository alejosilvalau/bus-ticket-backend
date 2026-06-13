package com.frro.bus.ticket.common.dto;

import org.springframework.data.domain.Page;

public record PaginationMeta(
        int currentPage,
        int pageSize,
        long totalPages,
        long totalItems) {

    public static PaginationMeta fromPage(Page<?> page) {
        return new PaginationMeta(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements());
    }
}
