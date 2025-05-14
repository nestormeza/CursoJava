package com.examen.examen.curso.domain.ports.in;

import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;

import java.util.List;

public interface ICompanyIn {
    CompanySunatDto createCompanyFeignIn(String ruc);
    CompanySunatDto createCompanyRetroIn(String ruc);
    CompanySunatDto createCompanyTemplateIn(String ruc);
    CompanySunatDto updateCompanyIn (CompanySunatDto company);
    CompanySunatDto getCompanyByIdIn (int id);
    CompanySunatDto deleteCompanyByIdIn (int id);
    List<CompanySunatDto> getAllCompaniesIn();
}
