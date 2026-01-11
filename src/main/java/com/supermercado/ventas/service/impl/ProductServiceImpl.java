package com.supermercado.ventas.service.impl;

import com.supermercado.ventas.entity.Producto;
import com.supermercado.ventas.exception.ErrorEnum;
import com.supermercado.ventas.repository.ProductoRepository;
import com.supermercado.ventas.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductoRepository productoRepository;

    @Override
    public Page<Producto> obtenerProductos(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Producto crearProducto(Producto producto) {
        existeProductoPorNombreOrException(producto.getNombre(), null);
        return productoRepository.save(producto);
    }


    @Override
    @Transactional
    public Producto actualizarProducto(Producto nuevoProducto, Long id) {
        Producto producto = obtenerProductoPorId(id);
        existeProductoPorNombreOrException(nuevoProducto.getNombre(), id);
        producto.setCategoria(nuevoProducto.getCategoria());
        producto.setNombre(nuevoProducto.getNombre());
        producto.setPrecio(nuevoProducto.getPrecio());
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Boolean eliminarProducto(Long id) {
        obtenerProductoPorId(id);
        productoRepository.deleteById(id);
        return true;
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElseThrow(() ->
                ErrorEnum.NOT_FOUND_RESOURCE.getException("El producto no existe")
        );
    }

    private void existeProductoPorNombreOrException(String nombre, Long id) {
        Boolean productNameExist = productoRepository.existsByNombreIgnoreCaseAndIdNot(nombre, id);
        if (productNameExist) {
            throw ErrorEnum.BAD_REQUEST.getException("El nombre del producto ya existe");
        }
    }
}
