package com.Tecsup.clase1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name="person")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String motherLastName;
    private String fullName;
    private String typeDocument;
    @Column(name = "number_document",unique = true)
    private String numberDocument;
    private String checkDigit;
    private String state;
    private Timestamp dateCreated;
    private String userCreated;
}
