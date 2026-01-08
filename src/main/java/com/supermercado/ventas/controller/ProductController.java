package com.supermercado.ventas.controller;

import com.supermercado.ventas.dto.producto.ProductoRequest;
import com.supermercado.ventas.dto.producto.ProductoResponse;
import com.supermercado.ventas.dto.rest.ApiResponse;
import com.supermercado.ventas.entity.Producto;
import com.supermercado.ventas.mapper.ProductoMapper;
import com.supermercado.ventas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductoMapper productoMapper;

    @GetMapping
    ResponseEntity<ApiResponse<List<ProductoResponse>>> obtenerProductos() {
        List<ProductoResponse> res = productService.obtenerProductos().stream().map(productoMapper::entityToResponse).toList();
        return ResponseEntity.ok(ApiResponse.ok(res));
    }

    @PostMapping
    ResponseEntity<ApiResponse<ProductoResponse>> crearProducto(@Validated @RequestBody ProductoRequest productoRequest) {
        Producto producto = productoMapper.requestToEntity(productoRequest);
        ProductoResponse res = productoMapper.entityToResponse(productService.crearProducto(producto));
        return ResponseEntity.ok(ApiResponse.ok("Producto creado con éxito", res));
    }
}
