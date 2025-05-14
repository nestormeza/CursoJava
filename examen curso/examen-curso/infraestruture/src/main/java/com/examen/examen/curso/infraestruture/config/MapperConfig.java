package com.examen.examen.curso.infraestruture.config;

import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import com.examen.examen.curso.infraestruture.entity.CompanyEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean(name="mapperDefault")
    public ModelMapper modelMapperDefault() {
        return new ModelMapper();
    }

    @Bean(name="mapperSunat")
    public ModelMapper SunatMapper(){
        ModelMapper  mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(CompanyEntity.class, CompanySunatDto.class)
                .addMapping(CompanyEntity::getName,(dest,v)->dest.setNameCompany((String)v))
                .addMapping(CompanyEntity::getDocument_type,(dest,v)->dest.setDocumentTypeCompany((String)v))
                .addMapping(CompanyEntity::getDocument_number,(dest,v)->dest.setDocumentNumberCompany((String)v))
                .addMapping(CompanyEntity::getState,(dest,v)->dest.setStateCompany((String)v))
                .addMapping(CompanyEntity::getAddress,(dest,v)->dest.setAddressCompany((String)v))
                .addMapping(CompanyEntity::getStatus,(dest,v)->dest.setStatusCompany((String)v))
                .addMapping(CompanyEntity::getUbigeo,(dest,v)->dest.setUbigeoCompany((String)v))
                .addMapping(CompanyEntity::getTrack_type,(dest,v)->dest.setTrackTypeCompany((String)v))
                .addMapping(CompanyEntity::getTrack_name,(dest,v)->dest.setTrackNameCompany((String)v))
                .addMapping(CompanyEntity::getZone_code,(dest,v)->dest.setZoneCodeCompany((String)v))
                .addMapping(CompanyEntity::getZone_type,(dest,v)->dest.setZoneTypeCompany((String)v))
                .addMapping(CompanyEntity::getNumber,(dest,v)->dest.setNumberCompany((String)v))
                .addMapping(CompanyEntity::getInterior,(dest,v)->dest.setInteriorCompany((String)v))
                .addMapping(CompanyEntity::getLot,(dest,v)->dest.setLotCompany((String)v))
                .addMapping(CompanyEntity::getApartment,(dest,v)->dest.setApartmentCompany((String)v))
                .addMapping(CompanyEntity::getBlock,(dest,v)->dest.setBlockCompany((String)v))
                .addMapping(CompanyEntity::getKilometer,(dest,v)->dest.setKilometerCompany((String)v))
                .addMapping(CompanyEntity::getDistrict,(dest,v)->dest.setDistrictCompany((String)v))
                .addMapping(CompanyEntity::getProvince,(dest,v)->dest.setProvinceCompany((String)v))
                .addMapping(CompanyEntity::getDepartment,(dest,v)->dest.setDepartmentCompany((String)v))
                .addMapping(CompanyEntity::getBranch_offices,(dest,v)->dest.setBranchOfficesCompany((String)v))
                .addMapping(CompanyEntity::getType,(dest,v)->dest.setTypeCompany((String)v))
                .addMapping(CompanyEntity::getEconomic_activity,(dest,v)->dest.setEconomicActivityCompany((String)v))
                .addMapping(CompanyEntity::getWorker_count,(dest,v)->dest.setWorkerCountCompany((String)v))
                .addMapping(CompanyEntity::getBilling_type,(dest,v)->dest.setBillingTypeCompany((String)v))
                .addMapping(CompanyEntity::getAccounting_type,(dest,v)->dest.setAccountingTypeCompany((String)v))
                .addMapping(CompanyEntity::getForeign_trade,(dest,v)->dest.setForeignTradeCompany((String)v));
        return mapper;
    }

    @Bean(name = "mapperEntity")
    public ModelMapper entityCompany(){
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(CompanySunatDto.class,CompanyEntity.class)
                .addMapping(CompanySunatDto::getNameCompany,(dest,v)->dest.setName((String)v))
                .addMapping(CompanySunatDto::getDocumentTypeCompany,(dest,v)->dest.setDocument_type((String)v))
                .addMapping(CompanySunatDto::getDocumentNumberCompany,(dest,v)-> dest.setDocument_number((String)v))
                .addMapping(CompanySunatDto::getStateCompany,(dest,v)->dest.setState((String)v))
                .addMapping(CompanySunatDto::getAddressCompany,(dest,v)->dest.setAddress((String)v))
                .addMapping(CompanySunatDto::getStatusCompany,(dest,v)->dest.setStatus((String)v))
                .addMapping(CompanySunatDto::getUbigeoCompany,(dest,v)->dest.setUbigeo((String)v))
                .addMapping(CompanySunatDto::getTrackTypeCompany,(dest,v)->dest.setTrack_type((String)v))
                .addMapping(CompanySunatDto::getTrackNameCompany,(dest,v)->dest.setTrack_name((String)v))
                .addMapping(CompanySunatDto::getZoneCodeCompany,(dest,v)->dest.setZone_code((String)v))
                .addMapping(CompanySunatDto::getZoneTypeCompany,(dest,v)->dest.setZone_type((String)v))
                .addMapping(CompanySunatDto::getNumberCompany,(dest,v)->dest.setNumber((String)v))
                .addMapping(CompanySunatDto::getInteriorCompany,(dest,v)->dest.setInterior((String)v))
                .addMapping(CompanySunatDto::getLotCompany,(dest,v)->dest.setLot((String)v))
                .addMapping(CompanySunatDto::getApartmentCompany,(dest,v)->dest.setApartment((String)v))
                .addMapping(CompanySunatDto::getBlockCompany,(dest,v)->dest.setBlock((String)v))
                .addMapping(CompanySunatDto::getKilometerCompany,(dest,v)->dest.setKilometer((String)v))
                .addMapping(CompanySunatDto::getDistrictCompany,(dest,v)->dest.setDistrict((String)v))
                .addMapping(CompanySunatDto::getProvinceCompany,(dest,v)->dest.setProvince((String)v))
                .addMapping(CompanySunatDto::getDepartmentCompany,(dest,v)->dest.setDepartment((String)v))
                .addMapping(CompanySunatDto::getBranchOfficesCompany,(dest,v)->dest.setBranch_offices((String)v))
                .addMapping(CompanySunatDto::getTypeCompany,(dest,v)->dest.setType((String)v))
                .addMapping(CompanySunatDto::getEconomicActivityCompany,(dest,v)->dest.setEconomic_activity((String)v))
                .addMapping(CompanySunatDto::getWorkerCountCompany,(dest,v)->dest.setWorker_count((String)v))
                .addMapping(CompanySunatDto::getBillingTypeCompany,(dest,v)->dest.setBilling_type((String)v))
                .addMapping(CompanySunatDto::getAccountingTypeCompany,(dest,v)->dest.setAccounting_type((String)v))
                .addMapping(CompanySunatDto::getForeignTradeCompany,(dest,v)->dest.setForeign_trade((String)v));
        return mapper;
    }
}
