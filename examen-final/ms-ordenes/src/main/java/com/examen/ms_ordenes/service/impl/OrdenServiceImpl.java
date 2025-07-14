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
import com.examen.ms_ordenes.utils.response.ResponseValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        ResponseValidate getValidate = authService.validateTokenAuth(token);
        authService.getRoles(getValidate,List.of("USER","ADMIN","SUPERADMIN"));
        try{
            OrdenEntity save= ordenRepository.save(convertOrdenEntity(requestOrden,token));
            return convertResponseOrden(save,token);
        } catch (Exception e){
            throw  new GlobalException(500,"Error al registrar orden");
        }
    }

    //convertir
    private ResponseOrden convertResponseOrden(OrdenEntity ordenEntity,String token){
        return ResponseOrden.builder()
                .userId(ordenEntity.getId())
                .products(searchProductAll(ordenEntity.getProductosIds(),token))
                .date(ordenEntity.getFecha())
        .build();
    }

    private OrdenEntity convertOrdenEntity(RequestOrden requestOrden,String token){
        List<ResponseProduct> productos = searchProductAll(requestOrden.getProducts(), token);

        List<Integer> productoIds = productos.stream()
                .map(ResponseProduct::getId) // Asumiendo que getId() devuelve Integer
                .collect(Collectors.toList());
        return OrdenEntity.builder()
                .usuarioId(requestOrden.getUser())
                .productosIds(productoIds)
                .fecha(LocalDateTime.now())
                .build();
    }


    //Obetener los productos
    private List<ResponseProduct> searchProductAll(List<Integer> ids, String token){
        List<ResponseProduct> products = new ArrayList<>();

        for (int id : ids) {
            ResponseProduct product = productService.validateProduct(id,token); // Validar uno por uno
            products.add(product);
        }

        return products;
    }


}
