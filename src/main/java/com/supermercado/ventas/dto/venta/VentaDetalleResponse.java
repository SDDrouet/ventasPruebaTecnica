package com.supermercado.ventas.dto.venta;

import com.supermercado.ventas.dto.producto.ProductoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class VentaDetalleResponse {
    Long id;
    BigDecimal precioProducto;
    int cantidad;
    BigDecimal total;
    ProductoResponse producto;
}
