package com.examen.ms_ordenes.utils.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ResponseOrden {
    private int id;
    private int iduser;
    private List<ResponseProduct> products;
    private LocalDateTime date;
}
