package com.supermercado.ventas.dto.sucursal;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DireccionRequest {
    @NotBlank String pais;
    @NotBlank String ciudad;
    @NotBlank String provincia;
    @NotBlank String sector;
    @NotBlank String callePrincipal;
    @NotBlank String calleSecuandaria;
}
