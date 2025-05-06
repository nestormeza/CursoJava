package com.Tecsup.clase1.repository;

import com.Tecsup.clase1.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IPersonRepository extends JpaRepository<PersonEntity,Long> {
    List<PersonEntity> findAllByState(String state);
}
