package com.examen.examen.curso.infraestruture.config;


import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import com.examen.examen.curso.infraestruture.exception.SunatCompanyException;
import com.examen.examen.curso.infraestruture.rest.IFeignClientApi;
import com.examen.examen.curso.infraestruture.utils.constants.Constants;
import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class FeignClientApi {
    private final IFeignClientApi apiNetClient;

    public SunatResponseDto searchApi(String numero,String token) {
        try {
            log.info(Constants.search + "FEIGN CLIENT");
            SunatResponseDto company = apiNetClient.getSunatCompany(numero,"Bearer "+token);
            if(company == null){
                throw new SunatCompanyException(400,Constants.error400+numero);
            }
            return company;
        }catch (Exception e){
            throw new SunatCompanyException(500, Constants.error500, e );
        }
    }
}
