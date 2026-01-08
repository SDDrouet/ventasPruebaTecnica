package com.supermercado.ventas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sucursales")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;

    @JoinColumn(name = "direccion_id")
    @OneToOne(fetch = FetchType.LAZY)
    Direccion direccion;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    Set<Venta> ventas = new HashSet<>();
}
