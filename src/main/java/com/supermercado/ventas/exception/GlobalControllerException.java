package com.supermercado.ventas.exception;

import com.supermercado.ventas.dto.rest.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalControllerException {

    @ExceptionHandler(ControlledException.class)
    ResponseEntity<ApiErrorResponse> controlledException(ControlledException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(
                new ApiErrorResponse(
                        false,
                        exception.getStatusCode(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                        false,
                        exception.getStatusCode().value(),
                        exception.getFieldErrors().stream()
                                .map((e) -> e.getField() + ": " + e.getDefaultMessage())
                                .collect(Collectors.joining(", "))
                )
        );
    }

    @ExceptionHandler(MissingPathVariableException.class)
    ResponseEntity<ApiErrorResponse> missingPathVariableException(MissingPathVariableException exception) {
        return ResponseEntity.status(ErrorEnum.BAD_REQUEST.httpStatus).body(
                new ApiErrorResponse(
                        false,
                        ErrorEnum.BAD_REQUEST.httpStatus.value(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiErrorResponse> runtimeException(Exception exception) {
        log.error(ErrorEnum.INTERNAL_SEVERAL_ERROR.name(), exception);
        return ResponseEntity.status(ErrorEnum.INTERNAL_SEVERAL_ERROR.httpStatus).body(
                new ApiErrorResponse(
                        false,
                        ErrorEnum.INTERNAL_SEVERAL_ERROR.httpStatus.value(),
                        ErrorEnum.INTERNAL_SEVERAL_ERROR.name()
                )
        );
    }
}
