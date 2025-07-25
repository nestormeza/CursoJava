package com.examen.ms_ordenes.utils.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ResponseOrden {
    private int userId;
    private List<ResponseProduct> products;
    private LocalDateTime date;
}
