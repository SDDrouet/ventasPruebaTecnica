package com.supermercado.ventas.controller;

import com.supermercado.ventas.dto.rest.ApiResponse;
import com.supermercado.ventas.dto.sucursal.SucursalRequest;
import com.supermercado.ventas.dto.sucursal.SucursalResponse;
import com.supermercado.ventas.entity.Sucursal;
import com.supermercado.ventas.mapper.SucursalMapper;
import com.supermercado.ventas.service.SucursalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sucursales")
@RequiredArgsConstructor
public class SucursalController {
    private final SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SucursalResponse>>> obtenerSucursales(@PageableDefault Pageable pageable) {
        Page<SucursalResponse> res = sucursalService.obtenerSucursales(pageable)
                .map(SucursalMapper::sucursalEntityToRes);
        return ResponseEntity.ok(ApiResponse.ok(
                res
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SucursalResponse>> crearSucursal(@Validated @RequestBody SucursalRequest sucursalRequest) {
        Sucursal sucursal = SucursalMapper.sucursalReqToEntity(sucursalRequest);
        SucursalResponse res = SucursalMapper.sucursalEntityToRes(sucursalService.crearSucursal(sucursal));
        return ResponseEntity.ok(ApiResponse.ok(
                "Sucursal creada correctamente",
                res
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SucursalResponse>> actualizarSucursal(@Validated @RequestBody SucursalRequest sucursalRequest,
                                                                            @PathVariable Long id) {
        Sucursal nuevaSucursal = SucursalMapper.sucursalReqToEntity(sucursalRequest);
        SucursalResponse res = SucursalMapper.sucursalEntityToRes(sucursalService.actualizarSucursal(nuevaSucursal, id));
        return ResponseEntity.ok(ApiResponse.ok(
                "Sucursal actualizada con éxito",
                res
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> inhabilitarSucursal(@PathVariable Long id) {
        Boolean res = sucursalService.inhabilitarSucursal(id);
        return ResponseEntity.ok(ApiResponse.ok(
                "Sucursales inhabilitada con éxito",
                res
        ));
    }
}
