package com.examen.examen.curso.infraestruture.config;

import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import com.examen.examen.curso.infraestruture.exception.SunatCompanyException;
import com.examen.examen.curso.infraestruture.rest.retroFit.IRetroFitClientApi;
import com.examen.examen.curso.infraestruture.rest.retroFit.RetrofitClientApi;
import com.examen.examen.curso.infraestruture.utils.constants.Constants;
import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Component
@Log4j2
public class RetroFitApi {
    IRetroFitClientApi retrofitApiConfig = RetrofitClientApi.getRetrofit().create(IRetroFitClientApi.class);

    public SunatResponseDto searchApi(String ruc, String token){
        try {
            log.info(Constants.search + "RETRO FIT");
            Response<SunatResponseDto> response = preparedClient(ruc,"Bearer "+token).execute();
            if(response.isSuccessful() && response.body() != null){
                return response.body();
            }
            throw  new SunatCompanyException(400, Constants.error400 + ruc);
        }catch (Exception e){
            throw new SunatCompanyException(500,Constants.error500, e );
        }
    }

    private Call<SunatResponseDto> preparedClient(String ruc, String token){
        return retrofitApiConfig.findSunat(token,ruc);
    }
}
