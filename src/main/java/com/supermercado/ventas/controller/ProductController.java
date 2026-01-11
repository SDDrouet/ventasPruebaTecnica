package com.supermercado.ventas.controller;

import com.supermercado.ventas.dto.producto.ProductoRequest;
import com.supermercado.ventas.dto.producto.ProductoResponse;
import com.supermercado.ventas.dto.rest.ApiResponse;
import com.supermercado.ventas.entity.Producto;
import com.supermercado.ventas.mapper.ProductoMapper;
import com.supermercado.ventas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    ResponseEntity<ApiResponse<Page<ProductoResponse>>> obtenerProductos(@PageableDefault Pageable pageable) {
        Page<ProductoResponse> res = productService.obtenerProductos(pageable)
                            .map(ProductoMapper::entityToResponse);
        return ResponseEntity.ok(ApiResponse.ok(res));
    }

    @PostMapping
    ResponseEntity<ApiResponse<ProductoResponse>> crearProducto(@Validated @RequestBody ProductoRequest productoRequest) {
        Producto producto = ProductoMapper.requestToEntity(productoRequest);
        ProductoResponse res = ProductoMapper.entityToResponse(productService.crearProducto(producto));
        return ResponseEntity.ok(ApiResponse.ok("Producto creado con éxito", res));
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<ProductoResponse>> actualizarProducto(@PathVariable Long id,
                                                                     @Validated @RequestBody ProductoRequest productoResquest) {
        Producto nuevoProducto = ProductoMapper.requestToEntity(productoResquest);
        ProductoResponse res = ProductoMapper.entityToResponse(productService.actualizarProducto(nuevoProducto, id));
        return ResponseEntity.ok(ApiResponse.ok(
                "Producto actualizado con éxito",
                res
        ));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Boolean>> eliminarProducto(@PathVariable Long id) {
        Boolean productoEliminado = productService.eliminarProducto(id);
        return ResponseEntity.ok(ApiResponse.ok(
                "Producto eliminado",
                productoEliminado
        ));
    }
}
