package com.supermercado.ventas.repository;

import com.supermercado.ventas.entity.VentaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {
}
