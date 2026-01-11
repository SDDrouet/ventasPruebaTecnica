package com.supermercado.ventas.dto.sucursal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SucursalResponse {
    Long id;
    String nombre;
    DireccionResponse direccion;
    String createdAt;
}
