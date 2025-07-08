package com.examen.ms_auth.repository;

import com.examen.ms_auth.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RolRepository extends JpaRepository<RolEntity,Integer> {
    Optional<RolEntity> findByName(String rol);
}
