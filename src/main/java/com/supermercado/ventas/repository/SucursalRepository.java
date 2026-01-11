package com.supermercado.ventas.repository;

import com.supermercado.ventas.entity.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    Optional<Sucursal> findByIdAndIsActive(Long id, Boolean isActive);

    Page<Sucursal> findAllByIsActive(Boolean isActive, Pageable pageable);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);
}
