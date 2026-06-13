package com.frro.bus.ticket.common.dto;

public record ApiResponse<T>(
        String message,
        T data,
        PaginationMeta pagination) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Success", data, null);
    }

    public static <T> ApiResponse<T> success(T data, PaginationMeta pagination) {
        return new ApiResponse<>("Success", data, pagination);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, null, null);
    }
}
