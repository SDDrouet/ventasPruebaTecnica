package com.supermercado.ventas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;

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
    @ManyToOne(fetch = FetchType.LAZY)
    Producto producto;

    @JoinColumn(name = "venta_id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Venta.class)
    Venta venta;

    @CreatedDate
    Instant createdAt;
    @LastModifiedDate
    Instant  updatedAt;

    @PrePersist
    void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
