package com.examen.ms_ordenes.entity;

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
    private List<Integer> id_product;
    private LocalDateTime fecha;
}
