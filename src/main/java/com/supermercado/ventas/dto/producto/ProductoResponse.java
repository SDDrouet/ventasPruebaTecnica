package com.supermercado.ventas.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class ProductoResponse{
        Long id;
        String nombre;
        BigDecimal precio;
        String categoria;
}
