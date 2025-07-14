package com.examen.ms_ordenes.entity;

import com.examen.ms_ordenes.utils.response.ResponseProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "odernes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int iduser;
    private List<ResponseProduct> id_product;
    private LocalDateTime fecha;
}
