package com.supermercado.ventas.dto.producto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class ProductoRequest {
        @NotBlank String nombre;
        @DecimalMin("0.01") BigDecimal precio;
        @NotBlank String categoria;
}
