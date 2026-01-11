package com.supermercado.ventas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
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
    Boolean isActive;

    @JoinColumn(name = "direccion_id")
    @OneToOne(fetch = FetchType.LAZY)
    Direccion direccion;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    Set<Venta> ventas = new HashSet<>();

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
