package com.supermercado.ventas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

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
