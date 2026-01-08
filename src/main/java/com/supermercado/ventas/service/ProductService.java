package com.supermercado.ventas.service;

import com.supermercado.ventas.dto.producto.ProductoRequest;
import com.supermercado.ventas.dto.producto.ProductoResponse;
import com.supermercado.ventas.entity.Producto;

import java.util.List;

public interface ProductService {
    List<Producto> obtenerProductos();
    Producto crearProducto(Producto producto);
}
