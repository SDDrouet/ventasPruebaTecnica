package com.supermercado.ventas.repository;

import com.supermercado.ventas.entity.Sucursal;
import com.supermercado.ventas.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findAllByIsActive(Boolean isActive);
    Page<Venta> findVentasBySucursalAndCreatedAtBeforeAndIsActive(Sucursal sucursal, Instant createdAtBefore, Boolean isActive, Pageable pageable);
}
