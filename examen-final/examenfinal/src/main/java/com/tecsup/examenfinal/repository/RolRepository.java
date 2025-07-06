package com.tecsup.examenfinal.repository;

import com.tecsup.examenfinal.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RolRepository extends JpaRepository<RolEntity,Integer> {
    Optional<RolEntity> findByName(String rol);
}
