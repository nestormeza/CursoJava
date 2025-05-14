package com.examen.examen.curso.domain.ports.out;


import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;

import java.util.List;

public interface ICompanyOut {
    CompanySunatDto createCompanyFeignOut(String ruc);
    CompanySunatDto createCompanyRetroOut(String ruc);
    CompanySunatDto createCompanyTemplateOut(String ruc);
    CompanySunatDto updateCompanyOut(CompanySunatDto company);
    CompanySunatDto getCompanyByIdOut(int id);
    CompanySunatDto deleteCompanyByIdOut(int id);
    List<CompanySunatDto> getAllCompaniesOut();
}
