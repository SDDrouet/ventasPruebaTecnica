package com.supermercado.ventas.dto.sucursal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DireccionResponse {
    Long id;
    String pais;
    String ciudad;
    String provincia;
    String sector;
    String callePrincipal;
    String calleSecuandaria;
}
