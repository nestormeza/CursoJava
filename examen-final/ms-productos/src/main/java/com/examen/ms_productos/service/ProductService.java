package com.examen.ms_productos.service;

import com.examen.ms_productos.entity.ProductEntity;
import com.examen.ms_productos.utils.request.ProductRequest;
import com.examen.ms_productos.utils.response.ResponseProduct;

import java.util.List;

public interface ProductService {
    ResponseProduct saveProduct(ProductRequest productRequest);
    ResponseProduct updateProduct(ProductEntity productEntity);
    ResponseProduct deleteProduct(int id);
    List<ResponseProduct> allProduct();
}

