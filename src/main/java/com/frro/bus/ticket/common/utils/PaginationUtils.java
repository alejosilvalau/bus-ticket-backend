package com.frro.bus.ticket.common.utils;

import com.frro.bus.ticket.common.dto.PageResponse;
import org.springframework.data.domain.Page;

public abstract class PaginationUtils {
    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.of(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty());
    }
}
