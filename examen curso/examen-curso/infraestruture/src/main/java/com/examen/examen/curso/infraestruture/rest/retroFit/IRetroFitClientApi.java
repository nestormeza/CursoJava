package com.examen.examen.curso.infraestruture.rest.retroFit;

import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IRetroFitClientApi {
    @GET("sunat/ruc/full")
    Call<SunatResponseDto> findSunat(@Header("Authorization") String token,
                                     @Query("numero") String numero);
}
