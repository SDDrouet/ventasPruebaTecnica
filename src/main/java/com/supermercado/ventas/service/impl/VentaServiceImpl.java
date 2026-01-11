package com.supermercado.ventas.service.impl;

import com.supermercado.ventas.constant.Constants;
import com.supermercado.ventas.entity.Producto;
import com.supermercado.ventas.entity.Sucursal;
import com.supermercado.ventas.entity.Venta;
import com.supermercado.ventas.entity.VentaDetalle;
import com.supermercado.ventas.exception.ErrorEnum;
import com.supermercado.ventas.repository.VentaRepository;
import com.supermercado.ventas.service.ProductService;
import com.supermercado.ventas.service.SucursalService;
import com.supermercado.ventas.service.VentaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;

    private final ProductService productService;
    private final SucursalService sucursalService;

    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) {
        Set<VentaDetalle> ventaDetallesRequest = venta.getVentaDetalles();
        Set<VentaDetalle> ventaDetalles = new HashSet<>();
        final BigDecimal[] subtotal = {BigDecimal.ZERO};

        Sucursal sucursal = sucursalService.obtenerSucursalPorId(venta.getSucursal().getId());
        venta.setSucursal(sucursal);

        ventaDetallesRequest.forEach((detalleReq) -> {
            Producto producto = productService.obtenerProductoPorId(detalleReq.getProducto().getId());
            int cantidad = detalleReq.getCantidad();
            BigDecimal precioUnitario = producto.getPrecio();
            BigDecimal total = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
            subtotal[0] = subtotal[0].add(total);
            ventaDetalles.add(VentaDetalle.builder()
                    .cantidad(cantidad)
                    .producto(producto)
                    .precioProducto(precioUnitario)
                    .total(total)
                    .venta(venta)
                    .build()
            );
        });

        BigDecimal total = getTotalVenta(venta, subtotal);

        venta.setVentaDetalles(ventaDetalles);
        venta.setIva(Constants.IVA);
        venta.setSubTotal(subtotal[0]);
        venta.setTotal(total);

        return ventaRepository.saveAndFlush(venta);
    }

    private @NonNull BigDecimal getTotalVenta(Venta venta, BigDecimal[] subtotal) {
        BigDecimal descuentoTotal = subtotal[0]
                .multiply(
                        venta.getDescuento().divide(
                                BigDecimal.valueOf(100),
                                Constants.SCALE_FOR_VENTAS,
                                Constants.ROUNDING_MODE_FOR_VENTAS
                        )
                );

        BigDecimal subtotalConDescuento = subtotal[0].subtract(descuentoTotal);
        BigDecimal ivaAplicar = subtotalConDescuento.multiply(Constants.IVA);
        return subtotalConDescuento.add(ivaAplicar);
    }

    @Override
    public Page<Venta> obtenerVentasPorSucursalYFecha(Pageable pageable, Long sucursalId, Date date) {
        Sucursal sucursal = sucursalService.obtenerSucursalPorId(sucursalId);
        return ventaRepository.findVentasBySucursalAndCreatedAtBeforeAndIsActive(sucursal, date.toInstant(), true, pageable);
    }

    @Override
    @Transactional
    public Boolean anularVenta(Long ventaId) {
        Venta venta = obtenerVentaPorId(ventaId);
        venta.setIsActive(false);
        return true;
    }

    private Venta obtenerVentaPorId(Long ventaId) {
        return ventaRepository.findById(ventaId).orElseThrow(() ->
            ErrorEnum.NOT_FOUND_RESOURCE.getException("No Se encontró la venta con id: " + ventaId)
        );
    }
}
