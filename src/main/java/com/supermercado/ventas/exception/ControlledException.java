package com.supermercado.ventas.exception;

import lombok.Getter;

@Getter
public class ControlledException extends RuntimeException {
    private final int statusCode;
    public ControlledException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
