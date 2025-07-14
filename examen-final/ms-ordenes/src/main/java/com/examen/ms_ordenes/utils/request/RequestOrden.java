package com.examen.ms_ordenes.utils.request;

import com.examen.ms_ordenes.utils.response.ResponseProduct;
import com.examen.ms_ordenes.utils.response.ResponseUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RequestOrden {
    private int user;
    private List<Integer> products;
}
