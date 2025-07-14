package com.examen.ms_ordenes.utils.request;

import com.examen.ms_ordenes.utils.response.ResponseProduct;
import com.examen.ms_ordenes.utils.response.ResponseUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestOrden {
    private ResponseUser user;
    private List<ResponseProduct> products;
}
