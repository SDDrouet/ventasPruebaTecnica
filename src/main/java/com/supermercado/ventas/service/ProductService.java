package com.supermercado.ventas.service;

import com.supermercado.ventas.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    Page<Producto> obtenerProductos(Pageable pageable);
    Producto crearProducto(Producto producto);
    Producto actualizarProducto(Producto nuevoProducto, Long id);
    Boolean eliminarProducto(Long id);
    Producto obtenerProductoPorId(Long id);
}
