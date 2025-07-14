package com.examen.ms_ordenes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orden")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int usuarioId;

    private LocalDateTime fecha;

    @ElementCollection
    @CollectionTable(name = "orden_productos", joinColumns = @JoinColumn(name = "orden_id"))
    @Column(name = "producto_id")
    private List<Integer> productosIds;
}
