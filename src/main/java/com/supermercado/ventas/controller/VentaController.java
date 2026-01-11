package com.supermercado.ventas.controller;

import com.supermercado.ventas.dto.rest.ApiResponse;
import com.supermercado.ventas.dto.venta.VentaRequest;
import com.supermercado.ventas.dto.venta.VentaResponse;
import com.supermercado.ventas.entity.Venta;
import com.supermercado.ventas.mapper.VentaMapper;
import com.supermercado.ventas.service.VentaService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<ApiResponse<VentaResponse>> registrarVenta(@Validated @RequestBody VentaRequest ventaRequest) {
        Venta req = VentaMapper.ventaReqToEntity(ventaRequest);
        VentaResponse ventaResponse = VentaMapper.ventaEntityToRes(ventaService.registrarVenta(req));
        return ResponseEntity.ok(ApiResponse.ok(
                "Venta registrada con éxito",
                ventaResponse
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<VentaResponse>>> obtenerVentasPorSucursalYFecha(
                                                        @PageableDefault Pageable pageable,
                                                        @NotNull @RequestParam Long sucursalId,
                                                        @RequestParam Date date
    ) {
        Page<VentaResponse> res = ventaService.obtenerVentasPorSucursalYFecha(pageable, sucursalId, date)
                .map(VentaMapper::ventaEntityToRes);
        return ResponseEntity.ok(ApiResponse.ok(
                res
        ));
    }

    @DeleteMapping("/{ventaId}")
    public ResponseEntity<ApiResponse<Boolean>> anularVenta(@PathVariable Long ventaId) {
        Boolean res = ventaService.anularVenta(ventaId);
        return ResponseEntity.ok(ApiResponse.ok(
                "Venta anulada con éxito",
                res
        ));
    }

}
