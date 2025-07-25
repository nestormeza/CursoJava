package com.examen.ms_ordenes.service;

import com.examen.ms_ordenes.utils.request.RequestOrden;
import com.examen.ms_ordenes.utils.response.ResponseOrden;

import java.util.List;

public interface OrdenService {
    List<ResponseOrden> allOrden(String token);
    ResponseOrden saveOrden(RequestOrden requestOrden,String token);
}
