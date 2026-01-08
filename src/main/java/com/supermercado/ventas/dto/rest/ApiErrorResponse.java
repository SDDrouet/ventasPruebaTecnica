package com.supermercado.ventas.dto.rest;

public record ApiErrorResponse(
        Boolean success,
        int statusCode,
        String error
) {
}
