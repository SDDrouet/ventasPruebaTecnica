package com.supermercado.ventas.controller;

import com.supermercado.ventas.dto.estadisiticas.ProductoMasVendidoResponse;
import com.supermercado.ventas.dto.rest.ApiResponse;
import com.supermercado.ventas.service.EstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {
    private final EstadisticasService estadisticasService;

    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<ApiResponse<ProductoMasVendidoResponse>> obtenerProductoMasVendido() {
        ProductoMasVendidoResponse res = estadisticasService.obtenerProductoMasVendido();
        return ResponseEntity.ok(ApiResponse.ok(
                res
        ));
    }
}
