package com.examen.ms_ordenes.service;

import com.examen.ms_ordenes.utils.response.ResponseProduct;

public interface ProductService {
    ResponseProduct validateProduct(int id,String token);
}
