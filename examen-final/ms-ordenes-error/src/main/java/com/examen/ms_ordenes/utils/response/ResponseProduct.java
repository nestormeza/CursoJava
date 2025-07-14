package com.examen.ms_ordenes.utils.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProduct {
    private int id;
    private String name;
    private double price;
    private String category;
}
