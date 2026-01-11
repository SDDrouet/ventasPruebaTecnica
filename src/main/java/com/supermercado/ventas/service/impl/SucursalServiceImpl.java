package com.supermercado.ventas.service.impl;

import com.supermercado.ventas.entity.Direccion;
import com.supermercado.ventas.entity.Sucursal;
import com.supermercado.ventas.exception.ErrorEnum;
import com.supermercado.ventas.repository.DireccionRepository;
import com.supermercado.ventas.repository.SucursalRepository;
import com.supermercado.ventas.service.SucursalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {
    private final SucursalRepository sucursalRepository;
    private final DireccionRepository direccionRepository;

    @Override
    public Page<Sucursal> obtenerSucursales(Pageable pageable) {
        return sucursalRepository.findAllByIsActive(true, pageable);
    }

    @Override
    @Transactional
    public Sucursal crearSucursal(Sucursal sucursal) {
        notExistSucursalByNameOrException(sucursal.getNombre(), null);
        Direccion direccion = direccionRepository.save(sucursal.getDireccion());
        sucursal.setDireccion(direccion);
        return sucursalRepository.save(sucursal);
    }

    @Override
    @Transactional
    public Sucursal actualizarSucursal(Sucursal nuevaSucursal, Long id) {
        Sucursal sucursal = obtenerSucursalPorId(id);
        notExistSucursalByNameOrException(nuevaSucursal.getNombre(), id);

        Direccion direccion = sucursal.getDireccion();
        direccion.setPais(nuevaSucursal.getDireccion().getPais());
        direccion.setCiudad(nuevaSucursal.getDireccion().getCiudad());
        direccion.setProvincia(nuevaSucursal.getDireccion().getProvincia());
        direccion.setSector(nuevaSucursal.getDireccion().getSector());
        direccion.setCallePrincipal(nuevaSucursal.getDireccion().getCallePrincipal());
        direccion.setCalleSecuandaria(nuevaSucursal.getDireccion().getCalleSecuandaria());

        direccionRepository.save(direccion);

        sucursal.setNombre(nuevaSucursal.getNombre());

        return sucursalRepository.save(sucursal);
    }

    @Override
    @Transactional
    public Boolean inhabilitarSucursal(Long id) {
        Sucursal sucursal = obtenerSucursalPorId(id);
        sucursal.setIsActive(false);
        sucursalRepository.save(sucursal);
        return true;
    }

    private void notExistSucursalByNameOrException(String nombre, Long id) {
        boolean existSucursal = sucursalRepository.existsByNombreIgnoreCaseAndIdNot(nombre, id);
        if (existSucursal) {
            throw ErrorEnum.VALIDATION_ERROR.getException("El nombre de la sucursal ya existe");
        }
    }

    public Sucursal obtenerSucursalPorId(Long id) {
        return sucursalRepository.findByIdAndIsActive(id, true).orElseThrow(() ->
                ErrorEnum.NOT_FOUND_RESOURCE.getException("Sucursal no encontrada"));
    }
}
