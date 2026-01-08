package com.supermercado.ventas.mapper;

import com.supermercado.ventas.dto.producto.ProductoRequest;
import com.supermercado.ventas.dto.producto.ProductoResponse;
import com.supermercado.ventas.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {
    @InheritInverseConfiguration
    ProductoResponse entityToResponse(Producto producto);
    Producto requestToEntity(ProductoRequest productoRequest);
}
