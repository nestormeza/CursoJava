package com.examen.ms_ordenes.service.impl;

import com.examen.ms_ordenes.service.OrdenService;
import com.examen.ms_ordenes.utils.request.RequestOrden;
import com.examen.ms_ordenes.utils.response.ResponseOrden;

import java.util.List;

public class OrdenServiceImpl implements OrdenService {
    @Override
    public ResponseOrden saveOrden(RequestOrden requestOrden, String token) {
        return null;
    }

    @Override
    public List<ResponseOrden> allOrden(String token) {
        return List.of();
    }
}
