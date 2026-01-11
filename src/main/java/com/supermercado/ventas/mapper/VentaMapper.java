package com.supermercado.ventas.mapper;

import com.supermercado.ventas.dto.venta.VentaDetalleRequest;
import com.supermercado.ventas.dto.venta.VentaDetalleResponse;
import com.supermercado.ventas.dto.venta.VentaRequest;
import com.supermercado.ventas.dto.venta.VentaResponse;
import com.supermercado.ventas.entity.Producto;
import com.supermercado.ventas.entity.Sucursal;
import com.supermercado.ventas.entity.Venta;
import com.supermercado.ventas.entity.VentaDetalle;
import com.supermercado.ventas.utils.DateUtils;

import java.util.stream.Collectors;

public class VentaMapper {
    public static Venta ventaReqToEntity(VentaRequest req) {
        if (req == null) return null;
        if (req.getVentaDetalles() == null) return null;
        return Venta.builder()
                .sucursal(Sucursal.builder().id(req.getSucursalId()).build())
                .nombreCliente(req.getNombreCliente())
                .dniCliente(req.getDniCliente())
                .descuento(req.getDescuento())
                .ventaDetalles(req.getVentaDetalles().stream()
                        .map(VentaMapper::ventaDetalleReqToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static VentaResponse ventaEntityToRes(Venta venta) {
        if (venta == null) return null;
        return VentaResponse.builder()
                .id(venta.getId())
                .nombreCliente(venta.getNombreCliente())
                .dniCliente(venta.getDniCliente())
                .subTotal(venta.getSubTotal())
                .iva(venta.getIva())
                .descuento(venta.getDescuento())
                .total(venta.getTotal())
                .detalle(venta.getVentaDetalles().stream()
                        .map(VentaMapper::ventaDetalleEntityToResponse)
                        .collect(Collectors.toSet()))
                .sucursal(SucursalMapper.sucursalEntityToRes(venta.getSucursal()))
                .createdAt(DateUtils.formatDate(venta.getCreatedAt()))
                .build();
    }

    public static VentaDetalle ventaDetalleReqToEntity(VentaDetalleRequest ventaDetalleRequest) {
        if (ventaDetalleRequest == null) return null;
        return VentaDetalle.builder()
                .cantidad(ventaDetalleRequest.getCantidad())
                .producto(Producto.builder().id(ventaDetalleRequest.getProductoId()).build())
                .build();
    }

    public static VentaDetalleResponse ventaDetalleEntityToResponse(VentaDetalle ventaDetalle) {
        if (ventaDetalle == null) return null;
        return VentaDetalleResponse.builder()
                .id(ventaDetalle.getId())
                .precioProducto(ventaDetalle.getPrecioProducto())
                .cantidad(ventaDetalle.getCantidad())
                .total(ventaDetalle.getTotal())
                .producto(ProductoMapper.entityToResponse(ventaDetalle.getProducto()))
                .build();
    }
}
