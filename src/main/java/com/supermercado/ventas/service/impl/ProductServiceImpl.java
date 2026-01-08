package com.supermercado.ventas.service.impl;

import com.supermercado.ventas.entity.Producto;
import com.supermercado.ventas.exception.ErrorEnum;
import com.supermercado.ventas.repository.ProductoRepository;
import com.supermercado.ventas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto crearProducto(Producto producto) {
        Boolean productNameExist = productoRepository.existsByNombre(producto.getNombre());

        if (productNameExist) {
            throw ErrorEnum.BAD_REQUEST.getException("El producto ya existe");
        }
        return productoRepository.save(producto);
    }
}
