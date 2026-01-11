package com.supermercado.ventas.mapper;

import com.supermercado.ventas.dto.producto.ProductoRequest;
import com.supermercado.ventas.dto.producto.ProductoResponse;
import com.supermercado.ventas.entity.Producto;

public class ProductoMapper {
    public static ProductoResponse entityToResponse(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoResponse.ProductoResponseBuilder productoResponse = ProductoResponse.builder();

        productoResponse.id( producto.getId() );
        productoResponse.nombre( producto.getNombre() );
        productoResponse.precio( producto.getPrecio() );
        productoResponse.categoria( producto.getCategoria() );

        return productoResponse.build();
    }

    public static Producto requestToEntity(ProductoRequest productoRequest) {
        if ( productoRequest == null ) {
            return null;
        }

        Producto.ProductoBuilder producto = Producto.builder();

        producto.nombre( productoRequest.getNombre() );
        producto.precio( productoRequest.getPrecio() );
        producto.categoria( productoRequest.getCategoria() );

        return producto.build();
    }
}
