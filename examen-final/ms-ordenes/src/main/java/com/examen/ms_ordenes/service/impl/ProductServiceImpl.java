package com.examen.ms_ordenes.service.impl;

import com.examen.ms_ordenes.config.FeingClientProductApi;
import com.examen.ms_ordenes.exception.GlobalException;
import com.examen.ms_ordenes.service.ProductService;
import com.examen.ms_ordenes.utils.constants.Constants;
import com.examen.ms_ordenes.utils.response.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final FeingClientProductApi feingClientProductApi;

    @Override
    public ResponseProduct validateProduct(int id,String token) {
        try {
            return feingClientProductApi.searchProduct(id,token);
        }catch (Exception ex){
            log.error("Error al registro : "+ex.getMessage(), ex);
            throw new GlobalException(500, Constants.ERROR_500);
        }
    }
}
