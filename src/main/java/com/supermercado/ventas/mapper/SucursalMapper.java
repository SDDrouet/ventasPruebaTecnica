package com.supermercado.ventas.mapper;

import com.supermercado.ventas.dto.sucursal.DireccionRequest;
import com.supermercado.ventas.dto.sucursal.DireccionResponse;
import com.supermercado.ventas.dto.sucursal.SucursalRequest;
import com.supermercado.ventas.dto.sucursal.SucursalResponse;
import com.supermercado.ventas.entity.Direccion;
import com.supermercado.ventas.entity.Sucursal;
import com.supermercado.ventas.utils.DateUtils;

public class SucursalMapper {
    public static Sucursal sucursalReqToEntity(SucursalRequest sucursalRequest) {
        if (sucursalRequest == null) return null;
        return Sucursal.builder()
                .nombre(sucursalRequest.getNombre())
                .direccion(direccionReqToEntity(sucursalRequest.getDireccion()))
                .build();
    }

    public static Direccion direccionReqToEntity(DireccionRequest request) {
        if (request == null) return null;
        return Direccion.builder()
                .pais(request.getPais())
                .ciudad(request.getCiudad())
                .provincia(request.getProvincia())
                .sector(request.getSector())
                .callePrincipal(request.getCallePrincipal())
                .calleSecuandaria(request.getCalleSecuandaria())
                .build();
    }

    public static SucursalResponse sucursalEntityToRes(Sucursal sucursal) {
        if (sucursal == null) return null;
        return SucursalResponse.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(direccionEntityToRes(sucursal.getDireccion()))
                .createdAt(DateUtils.formatDate(sucursal.getCreatedAt()))
                .build();
    }

    public static DireccionResponse direccionEntityToRes(Direccion direccion) {
        if (direccion == null) return null;
        return DireccionResponse.builder()
                .id(direccion.getId())
                .pais(direccion.getPais())
                .ciudad(direccion.getCiudad())
                .provincia(direccion.getProvincia())
                .sector(direccion.getSector())
                .callePrincipal(direccion.getCallePrincipal())
                .calleSecuandaria(direccion.getCalleSecuandaria())
                .build();
    }
}
