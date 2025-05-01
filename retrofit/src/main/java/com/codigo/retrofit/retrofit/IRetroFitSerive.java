package com.codigo.retrofit.retrofit;

import com.codigo.retrofit.aggregates.response.ReniecResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IRetroFitSerive {
    @GET("reniec/dni")
    Call<ReniecResponse> getReniecDni(
            @Header("Authorization") String token,
            @Query("numero") String numero
    );
}
