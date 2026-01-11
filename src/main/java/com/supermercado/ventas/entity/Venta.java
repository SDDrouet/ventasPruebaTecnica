package com.supermercado.ventas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombreCliente;
    String dniCliente;
    BigDecimal subTotal;
    BigDecimal iva;
    BigDecimal descuento;
    BigDecimal total;
    Boolean isActive;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    Set<VentaDetalle> ventaDetalles = new HashSet<>();

    @JoinColumn(name = "sucursal_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Sucursal sucursal;

    @CreatedDate
    Instant createdAt;
    @LastModifiedDate
    Instant  updatedAt;

    @PrePersist
    void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.isActive = true;
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
