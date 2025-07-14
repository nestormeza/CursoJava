package com.examen.ms_ordenes.respository;

import com.examen.ms_ordenes.entity.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<OrdenEntity,Integer> {
}
