package com.supermercado.ventas.dto.venta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class VentaRequest {
    @NotNull
    Long sucursalId;
    @NotBlank
    String nombreCliente;
    @NotBlank
    @Size(min = 10, max = 10, message = "Debe ser un formato dni válido")
    String dniCliente;
    @DecimalMin("0")
    @DecimalMax("100")
    BigDecimal descuento;
    @NotEmpty
    Set<@Valid VentaDetalleRequest> ventaDetalles;
}
