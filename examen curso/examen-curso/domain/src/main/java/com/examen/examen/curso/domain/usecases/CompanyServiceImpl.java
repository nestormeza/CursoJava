package com.examen.examen.curso.domain.usecases;

import com.examen.examen.curso.domain.ports.in.ICompanyIn;
import com.examen.examen.curso.domain.ports.out.ICompanyOut;
import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyServiceImpl implements ICompanyIn {
    private final ICompanyOut companyOut;

    @Override
    public CompanySunatDto createCompanyFeignIn(String ruc) {
        return companyOut.createCompanyFeignOut(ruc);
    }

    @Override
    public CompanySunatDto createCompanyRetroIn(String ruc) {
        return companyOut.createCompanyRetroOut(ruc);
    }

    @Override
    public CompanySunatDto createCompanyTemplateIn(String ruc) {
        return companyOut.createCompanyTemplateOut(ruc);
    }

    @Override
    public CompanySunatDto updateCompanyIn(CompanySunatDto company) {
        return companyOut.updateCompanyOut(company);
    }

    @Override
    public CompanySunatDto getCompanyByIdIn(int id) {
        return companyOut.getCompanyByIdOut(id);
    }

    @Override
    public CompanySunatDto deleteCompanyByIdIn(int id) {
        return companyOut.deleteCompanyByIdOut(id);
    }

    @Override
    public List<CompanySunatDto> getAllCompaniesIn() {
        return companyOut.getAllCompaniesOut();
    }
}
