package com.supermercado.ventas.service.impl;

import com.supermercado.ventas.dto.estadisiticas.ProductoMasVendidoResponse;
import com.supermercado.ventas.dto.producto.ProductoResponse;
import com.supermercado.ventas.entity.Producto;
import com.supermercado.ventas.entity.Venta;
import com.supermercado.ventas.mapper.ProductoMapper;
import com.supermercado.ventas.repository.VentaRepository;
import com.supermercado.ventas.service.EstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EstadisticasServiceImpl implements EstadisticasService {
    private final VentaRepository ventaRepository;

    @Override
    public ProductoMasVendidoResponse obtenerProductoMasVendido() {
        List<Venta> ventas = ventaRepository.findAllByIsActive(true);
        HashMap<Producto, BigDecimal> totalVendidoPorProducto = new HashMap<>();
        HashMap<Producto, Integer> cantidadVendidaPorProducto = new HashMap<>();

        ventas.forEach((venta ->
                venta.getVentaDetalles().forEach((ventaDetalle -> {
                    totalVendidoPorProducto.compute(ventaDetalle.getProducto(), (producto, precioAnterior) -> {
                        if (precioAnterior == null) return ventaDetalle.getPrecioProducto();
                        return precioAnterior.add(ventaDetalle.getPrecioProducto());
                    });

                    cantidadVendidaPorProducto.compute(ventaDetalle.getProducto(), (producto, cantidad) -> {
                        if (cantidad == null) return ventaDetalle.getCantidad();
                        return cantidad + ventaDetalle.getCantidad();
                    });
                }))
        ));

        Map.Entry<Producto, BigDecimal> productoMasVendido = totalVendidoPorProducto.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow();

        Integer cantidadVendida = cantidadVendidaPorProducto.get(productoMasVendido.getKey());
        BigDecimal totalVendido = productoMasVendido.getValue();
        ProductoResponse producto = ProductoMapper.entityToResponse(productoMasVendido.getKey());

        return ProductoMasVendidoResponse.builder()
                .producto(producto)
                .totalVendido(totalVendido)
                .cantidadVendido(cantidadVendida)
                .build();
    }
}
