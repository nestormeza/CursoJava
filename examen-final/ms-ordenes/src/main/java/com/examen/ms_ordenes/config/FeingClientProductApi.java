package com.examen.ms_ordenes.config;

import com.examen.ms_ordenes.exception.GlobalException;
import com.examen.ms_ordenes.rest.FeingClienProduct;
import com.examen.ms_ordenes.utils.constants.Constants;
import com.examen.ms_ordenes.utils.response.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Log4j2
public class FeingClientProductApi {
    private final FeingClienProduct feingClienProduct;

    public ResponseProduct searchProduct(int id, String token){
        try {
            log.info("Buscando productos...");
            ResponseProduct result = feingClienProduct.getvalidateProduct(id,token);
            if (result == null ) {
                throw new GlobalException(401, "Producto no encontrado ID: "+id);
            }
            return result;
        }catch (Exception e){
            log.error("Error al validar producto {} "+id +" error: "+e.getMessage(), e);
            throw new GlobalException(500, Constants.ERROR_500);
        }
    }
}
