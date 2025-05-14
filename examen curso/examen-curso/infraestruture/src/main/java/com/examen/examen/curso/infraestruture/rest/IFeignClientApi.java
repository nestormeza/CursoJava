package com.examen.examen.curso.infraestruture.rest;

import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-sunat",url = "${api.net}")
public interface IFeignClientApi {

    @GetMapping("/sunat/ruc/full")
    SunatResponseDto getSunatCompany(
            @RequestParam("numero") String numero,
            @RequestHeader("Authorization") String Authorization
    );
}
