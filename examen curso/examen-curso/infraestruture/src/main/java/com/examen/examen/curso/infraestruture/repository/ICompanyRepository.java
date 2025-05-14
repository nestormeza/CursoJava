package com.examen.examen.curso.infraestruture.repository;


import com.examen.examen.curso.infraestruture.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<CompanyEntity, Integer> {
}
