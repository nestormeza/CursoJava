package com.examen.ms_productos.config;


import com.examen.ms_productos.exception.GlobalException;
import com.examen.ms_productos.rest.FeignClientAuth;
import com.examen.ms_productos.utils.response.ResponseValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Log4j2
public class FeignClientApi {
    private final FeignClientAuth feignClientAuth;
    public ResponseValidate validateToken(String token) {
        try {
            log.info("Validando...");
            ResponseValidate result = feignClientAuth.getvalidateToken(token);
            if (result == null || !Boolean.TRUE.equals(result.getState())) {
                throw new GlobalException(401, "Token inválido");
            }

            return result;
        }catch (Exception e){
            log.error("Error al validar token", e);
            throw new GlobalException(500,"Error");
        }
    }
}
