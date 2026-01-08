package com.supermercado.ventas.repository;

import com.supermercado.ventas.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
