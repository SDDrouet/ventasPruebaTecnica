package com.supermercado.ventas.dto.rest;

public record ApiResponse<T>(
        Boolean success,
        String message,
        T data
) {
    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse(true, message, data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse(true, "Operación correcta", data);
    }
}
