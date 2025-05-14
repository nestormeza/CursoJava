package com.examen.examen.curso.infraestruture.adapters;


import com.examen.examen.curso.domain.ports.out.ICompanyOut;
import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import com.examen.examen.curso.infraestruture.config.CompanyBuilder;
import com.examen.examen.curso.infraestruture.config.FeignClientApi;
import com.examen.examen.curso.infraestruture.config.RestTemplateApi;
import com.examen.examen.curso.infraestruture.config.RetroFitApi;
import com.examen.examen.curso.infraestruture.entity.CompanyEntity;
import com.examen.examen.curso.infraestruture.exception.SunatCompanyException;
import com.examen.examen.curso.infraestruture.repository.ICompanyRepository;
import com.examen.examen.curso.infraestruture.utils.constants.Constants;
import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class CompanyAdapter implements ICompanyOut {
    private final FeignClientApi feignClientApi;
    private final ICompanyRepository companyRepository;
    private final ModelMapper sunatMapper;
    private final ModelMapper mapperEntity;
    private final ModelMapper mapperdefault;
    private final CompanyBuilder companyBuilder;
    private final RetroFitApi retrofitApi;
    private final RestTemplateApi restTemplateApi;

    @Value("${value.token}")
    String token;

    public CompanyAdapter(
            FeignClientApi feignClientApi,
            ICompanyRepository companyRepository,
            @Qualifier("mapperSunat") ModelMapper sunatMapper,
            @Qualifier("mapperEntity") ModelMapper mapperEntity,
            @Qualifier("mapperDefault") ModelMapper mapperdefault,
            CompanyBuilder companyBuilder, RetroFitApi retrofitApi,
            RestTemplateApi restTemplateApi) {
        this.feignClientApi = feignClientApi;
        this.companyRepository = companyRepository;
        this.sunatMapper = sunatMapper;
        this.mapperEntity = mapperEntity;
        this.mapperdefault = mapperdefault;
        this.companyBuilder = companyBuilder;
        this.retrofitApi = retrofitApi;
        this.restTemplateApi = restTemplateApi;
    }

    @Override
    public CompanySunatDto createCompanyFeignOut(String ruc) {
        //Obtener los datos de la api feign client
        SunatResponseDto companySunat= feignClientApi.searchApi(ruc,token);
        return saveCompany(companySunat);
    }

    @Override
    public CompanySunatDto createCompanyRetroOut(String ruc) {
        //obetener datos del retro fit
        SunatResponseDto companySunat= retrofitApi.searchApi(ruc,token);
        return saveCompany(companySunat);
    }

    @Override
    public CompanySunatDto createCompanyTemplateOut(String ruc) {
        //obtener los datos de api rest template
        SunatResponseDto companySunat= restTemplateApi.searchApi(ruc,token);
        return saveCompany(companySunat);
    }

    @Override
    public CompanySunatDto updateCompanyOut(CompanySunatDto company) {
        try {
            CompanyEntity companyEntity = searchCompany(company.getId());
            mapperEntity.map(company, companyEntity);                     // actualiza campos
            CompanyEntity updatedEntity = companyRepository.save(companyEntity);
            return mapToCompanySunatDTO(updatedEntity);
        }catch (Exception e){
            throw new SunatCompanyException(400,Constants.error400);
        }
    }

    @Override
    public CompanySunatDto getCompanyByIdOut(int id) {
        CompanyEntity company =searchCompany(id);
        return mapToCompanySunatDTO(company);
    }

    @Override
    public CompanySunatDto deleteCompanyByIdOut(int id) {
        CompanyEntity company =searchCompany(id);
        companyRepository.delete(company);
        return mapToCompanySunatDTO(company);
    }

    @Override
    public List<CompanySunatDto> getAllCompaniesOut() {
        List<CompanyEntity> companyEntities = companyRepository.findAll();
        return companyEntities.stream()
                .map(this::mapToCompanySunatDTO)
                .collect(Collectors.toList());
    }

    private CompanySunatDto mapToCompanySunatDTO(CompanyEntity companyEntity) {
        return sunatMapper.map(companyEntity,CompanySunatDto.class);
    }

    //guardar empresa
    private CompanySunatDto saveCompany(SunatResponseDto companySunat) {
        try{
            //builder
            CompanyEntity company= companyBuilder.CompanyEntitySave(companySunat);
            log.info("Guardando empresa en la base de datos");
            CompanyEntity companySave= companyRepository.save(company);
            //mapper
            return mapToCompanySunatDTO(companySave);
        }catch (Exception e){
            throw new SunatCompanyException(500,Constants.error500);
        }
    }

    //search empresa en base de datos
    private CompanyEntity searchCompany(int id) {
        return companyRepository.findById(id).orElseThrow(() -> new SunatCompanyException(404, Constants.error404));
    }
}
