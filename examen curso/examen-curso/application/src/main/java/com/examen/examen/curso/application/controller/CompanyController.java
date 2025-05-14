package com.examen.examen.curso.application.controller;

import com.examen.examen.curso.domain.ports.in.ICompanyIn;
import com.examen.examen.curso.domain.utils.dtos.CompanySunatDto;
import com.examen.examen.curso.infraestruture.adapters.CompanyAdapter;
import com.examen.examen.curso.infraestruture.utils.constants.Constants;
import com.examen.examen.curso.infraestruture.utils.dto.ResponseStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final ICompanyIn companyIn;

    @PostMapping("/save/feign/{ruc}")
    public ResponseEntity<ResponseStandard<CompanySunatDto>> saveFeign(@PathVariable String ruc){
        CompanySunatDto company= companyIn.createCompanyFeignIn(ruc);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.successSave,company));
    }

    @PostMapping("/save/retro/{ruc}")
    public ResponseEntity<ResponseStandard<CompanySunatDto>> saveRetro(@PathVariable String ruc){
        CompanySunatDto company= companyIn.createCompanyRetroIn(ruc);
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.successSave,company));
    }

    @PostMapping("/save/template/{ruc}")
    public ResponseEntity<ResponseStandard<CompanySunatDto>> saveTemplate(@PathVariable String ruc){
        CompanySunatDto company= companyIn.createCompanyTemplateIn(ruc);
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.successSave,company));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseStandard<CompanySunatDto>> findById(@PathVariable int id){
        CompanySunatDto company = companyIn.getCompanyByIdIn(id);
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.successSearch,company));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStandard<CompanySunatDto>> deleteById(@PathVariable int id){
        CompanySunatDto company = companyIn.deleteCompanyByIdIn(id);
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.successDelete,company));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStandard<CompanySunatDto>> updateById(@RequestBody CompanySunatDto company){
        CompanySunatDto companyupdate = companyIn.updateCompanyIn(company);
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.successUpdate,companyupdate));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseStandard<List<CompanySunatDto>>> findAll(){
        List<CompanySunatDto>  companies = companyIn.getAllCompaniesIn();
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.successSearch,companies));
    }
}
