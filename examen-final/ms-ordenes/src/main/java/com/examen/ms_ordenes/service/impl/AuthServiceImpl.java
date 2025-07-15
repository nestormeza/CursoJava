package com.examen.ms_ordenes.service.impl;

import com.examen.ms_ordenes.config.FeingClientAuthApi;

import com.examen.ms_ordenes.exception.GlobalException;
import com.examen.ms_ordenes.service.AuthService;
import com.examen.ms_ordenes.utils.constants.Constants;
import com.examen.ms_ordenes.utils.response.ResponseValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {
    private final FeingClientAuthApi feingClientAuthApi;

    @Override
    public ResponseValidate validateTokenAuth(String token) {
        try {
            // Quitar "Bearer "
            String convertToken = token.replace("Bearer ", "");

            ResponseValidate result = feingClientAuthApi.validateToken(convertToken);

            if(!result.getState()){
                throw new GlobalException(403,Constants.TOKEN_INVALIDO);
            }
            return result;
        }catch (Exception e){
            log.error("Error al validar token", e);
            throw new GlobalException(500, Constants.ERROR_500);
        }
    }

    @Override
    public void getRoles(ResponseValidate data, List<String> roles) {
        boolean hasRole = data.getRoles().stream().anyMatch(roles::contains);
        if (!hasRole) {
            throw new GlobalException(403,Constants.AUTHORIZATION_INVALIDA);
        }
    }
}
