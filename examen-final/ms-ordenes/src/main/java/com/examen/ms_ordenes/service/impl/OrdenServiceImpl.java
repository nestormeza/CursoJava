package com.examen.ms_ordenes.service.impl;

import com.examen.ms_ordenes.entity.OrdenEntity;
import com.examen.ms_ordenes.exception.GlobalException;
import com.examen.ms_ordenes.respository.OrdenRepository;
import com.examen.ms_ordenes.service.AuthService;
import com.examen.ms_ordenes.service.OrdenService;
import com.examen.ms_ordenes.service.ProductService;
import com.examen.ms_ordenes.utils.request.RequestOrden;
import com.examen.ms_ordenes.utils.response.ResponseOrden;
import com.examen.ms_ordenes.utils.response.ResponseProduct;
import com.examen.ms_ordenes.utils.response.ResponseUser;
import com.examen.ms_ordenes.utils.response.ResponseValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrdenServiceImpl implements OrdenService {
    private final AuthService authService;
    private final ProductService productService;
    private final OrdenRepository ordenRepository;

    @Override
    public List<ResponseOrden> allOrden(String token) {
        ResponseValidate getValidate = authService.validateTokenAuth(token);
        authService.getRoles(getValidate,List.of("ADMIN","SUPERADMIN"));
        try {
            return ordenRepository.findAll().stream()
                    .map(orden -> convertResponseOrden(orden,token))
                    .toList();
        }catch (Exception ex){
            throw  new GlobalException(500,"Error al listar ordenes");
        }
    }

    @Override
    public ResponseOrden saveOrden(RequestOrden requestOrden,String token) {
        return null;
    }

    //convertir a respon Ordenes
    private ResponseOrden convertResponseOrden(OrdenEntity ordenEntity,String token){
        return ResponseOrden.builder()
                .userId(ordenEntity.getId())
                .products(allProduct(ordenEntity,token))
                .date(ordenEntity.getFecha())
        .build();
    }

    //Obetener los productos
    private List<ResponseProduct> allProduct(OrdenEntity ordenEntity,String token){
        List<Integer> productRequest = ordenEntity.getProductosIds();
        List<ResponseProduct> products = new ArrayList<>();

        for (int id : productRequest) {
            ResponseProduct product = productService.validateProduct(id,token); // Validar uno por uno
            products.add(product);
        }

        return products;
    }

}
