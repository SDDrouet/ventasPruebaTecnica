package com.supermercado.ventas.service;

import com.supermercado.ventas.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface VentaService {
    Venta registrarVenta(Venta venta);
    Page<Venta> obtenerVentasPorSucursalYFecha(Pageable pageable, Long sucursalId, Date date);
    Boolean anularVenta(Long ventaId);
}
