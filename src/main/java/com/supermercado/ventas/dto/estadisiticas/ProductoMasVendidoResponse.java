package com.supermercado.ventas.dto.estadisiticas;

import com.supermercado.ventas.dto.producto.ProductoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class ProductoMasVendidoResponse {
    ProductoResponse producto;
    BigDecimal totalVendido;
    Integer cantidadVendido;
}
