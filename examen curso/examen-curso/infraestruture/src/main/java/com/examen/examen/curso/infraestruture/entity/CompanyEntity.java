package com.examen.examen.curso.infraestruture.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String document_type;
    private String document_number;
    private String state;
    private String address;
    private String status;
    private String ubigeo;
    private String track_type;
    private String track_name;
    private String zone_code;
    private String zone_type;
    private String number;
    private String interior;
    private String lot;
    private String apartment;
    private String block;
    private String kilometer;
    private String district;
    private String province;
    private String department;
    private String branch_offices;
    private String type;
    private String economic_activity;
    private String worker_count;
    private String billing_type;
    private String accounting_type;
    private String foreign_trade;
}
