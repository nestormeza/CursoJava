package com.examen.ms_productos.repository;

import com.examen.ms_productos.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    Optional<ProductEntity> findByName(String name);
}
