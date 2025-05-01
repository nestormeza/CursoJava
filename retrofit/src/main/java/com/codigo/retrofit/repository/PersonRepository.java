package com.codigo.retrofit.repository;

import com.codigo.retrofit.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
}
