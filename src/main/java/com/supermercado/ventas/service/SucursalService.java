package com.supermercado.ventas.service;

import com.supermercado.ventas.entity.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SucursalService {
    Page<Sucursal> obtenerSucursales(Pageable pageable);
    Sucursal crearSucursal(Sucursal sucursal);
    Sucursal actualizarSucursal(Sucursal nuevaSucursal, Long id);
    Boolean inhabilitarSucursal(Long id);
    Sucursal obtenerSucursalPorId(Long id);
}
