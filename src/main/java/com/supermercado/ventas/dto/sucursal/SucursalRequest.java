package com.supermercado.ventas.dto.sucursal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SucursalRequest {
    @NotBlank String nombre;
    @Valid DireccionRequest direccion;
}
