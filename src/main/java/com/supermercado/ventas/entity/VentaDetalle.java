package com.supermercado.ventas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venta_detalles")
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal precioProducto;
    int cantidad;
    BigDecimal total;

    @JoinColumn(name = "producto_id")
    @OneToOne(fetch = FetchType.LAZY)
    Producto producto;

    @JoinColumn(name = "venta_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Venta venta;
}
