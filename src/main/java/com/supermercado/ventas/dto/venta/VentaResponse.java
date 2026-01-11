package com.supermercado.ventas.dto.venta;

import com.supermercado.ventas.dto.sucursal.SucursalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class VentaResponse {
    Long id;
    String nombreCliente;
    String dniCliente;
    BigDecimal subTotal;
    BigDecimal iva;
    BigDecimal descuento;
    BigDecimal total;
    Set<VentaDetalleResponse> detalle;
    SucursalResponse sucursal;
    String createdAt;
}
