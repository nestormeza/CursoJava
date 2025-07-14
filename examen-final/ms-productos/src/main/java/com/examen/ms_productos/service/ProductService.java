package com.examen.ms_productos.service;

import com.examen.ms_productos.entity.ProductEntity;
import com.examen.ms_productos.utils.request.ProductRequest;
import com.examen.ms_productos.utils.response.ResponseProduct;

import java.util.List;

public interface ProductService {
    ResponseProduct saveProduct(String token,ProductRequest productRequest);
    ResponseProduct updateProduct(String token,ProductEntity productEntity);
    ResponseProduct deleteProduct(String token,int id);
    List<ResponseProduct> allProduct(String token);
    ProductEntity validateProduct(int id,String token);
}

