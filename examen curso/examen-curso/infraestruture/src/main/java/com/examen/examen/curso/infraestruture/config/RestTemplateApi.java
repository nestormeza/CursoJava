package com.examen.examen.curso.infraestruture.config;

import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import com.examen.examen.curso.infraestruture.exception.SunatCompanyException;
import com.examen.examen.curso.infraestruture.utils.constants.Constants;
import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

@Component
@Log4j2
@RequiredArgsConstructor
public class RestTemplateApi {
    private final RestTemplate restTemplate;

    @Value("${api.net}")
    private String baseUrl;

    public SunatResponseDto searchApi(String ruc, String token){
        String url = this.baseUrl+"/sunat/ruc/full?numero="+ruc;
        try {
            log.info(Constants.search + "RES TEMPLATE");
            ResponseEntity<SunatResponseDto> company = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders(token)),
                    SunatResponseDto.class
            );

            if(company.getStatusCode() == HttpStatus.OK){
                return company.getBody();
            }else {
                throw new SunatCompanyException(400, Constants.error400 + ruc);
            }
        }catch (Exception e){
            throw new SunatCompanyException(500,Constants.error500, e );
        }
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+token);
        return headers;
    };
}
