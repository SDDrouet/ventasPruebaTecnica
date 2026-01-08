package com.supermercado.ventas.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorEnum {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    NOT_FOUND_RESOURCE(HttpStatus.NOT_FOUND),
    INTERNAL_SEVERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    final HttpStatus httpStatus;

    public ControlledException getException(String message) {
        return new ControlledException(message, this.httpStatus.value());
    }

    public ControlledException getException() {
        return new ControlledException(this.name(), this.httpStatus.value());
    }
}
