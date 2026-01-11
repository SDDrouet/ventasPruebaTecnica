package com.supermercado.ventas.dto.venta;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class VentaDetalleRequest {
    @NotNull
    Long productoId;
    @NotNull
    @Min(1)
    @Max(500)
    int cantidad;
}
