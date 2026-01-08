package com.supermercado.ventas.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String pais;
    String ciudad;
    String provincia;
    String sector;
    String callePrincipal;
    String calleSecuandaria;

    @OneToOne(mappedBy = "direccion", cascade = CascadeType.ALL)
    Sucursal sucursal;
}
