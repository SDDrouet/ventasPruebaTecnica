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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    BigDecimal precio;
    String categoria;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    VentaDetalle ventaDetalle;
}
