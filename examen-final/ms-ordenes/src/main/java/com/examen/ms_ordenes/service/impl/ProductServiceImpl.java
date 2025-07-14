package com.examen.ms_ordenes.service.impl;

import com.examen.ms_ordenes.exception.GlobalException;
import com.examen.ms_ordenes.service.AuthService;
import com.examen.ms_ordenes.service.ProductService;
import com.examen.ms_ordenes.utils.response.ResponseProduct;
import com.examen.ms_ordenes.utils.response.ResponseValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final AuthService authService;

    @Override
    public ResponseProduct validateProduct(int id,String token) {
        ResponseValidate validateToken = authService.validateTokenAuth(token);
        try {
            return null;
        }catch (Exception ex){
            throw new GlobalException(500,"Error al obtener el producto");
        }
    }
}
