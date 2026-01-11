package com.supermercado.ventas.repository;

import com.supermercado.ventas.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);
}
