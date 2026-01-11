package com.supermercado.ventas.service;

import com.supermercado.ventas.dto.estadisiticas.ProductoMasVendidoResponse;

public interface EstadisticasService {
    ProductoMasVendidoResponse obtenerProductoMasVendido();
}
