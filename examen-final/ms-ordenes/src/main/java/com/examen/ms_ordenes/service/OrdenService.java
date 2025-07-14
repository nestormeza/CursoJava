package com.examen.ms_ordenes.service;

import com.examen.ms_ordenes.utils.request.RequestOrden;
import com.examen.ms_ordenes.utils.response.ResponseOrden;

import java.util.List;

public interface OrdenService {
    ResponseOrden saveOrden(RequestOrden requestOrden,String token);
    List<ResponseOrden> allOrden(String token);
}
