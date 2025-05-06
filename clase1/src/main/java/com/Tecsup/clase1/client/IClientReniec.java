package com.Tecsup.clase1.client;


import com.Tecsup.clase1.aggregates.response.ReniecResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="client-reniec",url="${api.url}")
public interface  IClientReniec {

    //https://api.apis.net.pe/v2/reniec/dni?numero=74470396
    @GetMapping(value = "/reniec/dni", produces = "application/json")
    ReniecResponse getPerson(
            @RequestParam("numero") String numero,
            @RequestHeader("Authorization") String authorization
    );
}
