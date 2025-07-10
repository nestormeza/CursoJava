package com.examen.ms_productos.utils.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseProduct {
    private int id;
    private String name;
    private double price;
    private String category;
}
