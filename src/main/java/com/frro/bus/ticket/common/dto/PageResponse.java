package com.frro.bus.ticket.common.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        boolean empty
) {
    public static <T> PageResponse<T> of(List<T> content, int pageNumber, int pageSize,
            long totalElements, int totalPages, boolean first, boolean last, boolean empty) {
        return new PageResponse<>(content, pageNumber, pageSize, totalElements, totalPages, first, last, empty);
    }
}
