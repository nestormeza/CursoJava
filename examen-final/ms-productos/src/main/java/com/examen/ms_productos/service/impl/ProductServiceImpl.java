package com.examen.ms_productos.service.impl;

import com.examen.ms_productos.entity.ProductEntity;
import com.examen.ms_productos.exception.GlobalException;
import com.examen.ms_productos.repository.ProductRepository;
import com.examen.ms_productos.service.ProductService;
import com.examen.ms_productos.utils.constants.Constants;
import com.examen.ms_productos.utils.request.ProductRequest;
import com.examen.ms_productos.utils.response.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ResponseProduct saveProduct(ProductRequest productRequest) {
        validateNewProduct(convertProductEntity(productRequest));
        ProductEntity productNew = convertProductEntity(productRequest);
        return registerProduct(productNew);
    }

    @Override
    public ResponseProduct updateProduct(ProductEntity productEntity) {
        validateUpdateProduct(productEntity);
        return registerProduct(productEntity);
    }

    @Override
    public ResponseProduct deleteProduct(int id) {
        ProductEntity product = searchProduct(id);
        log.info("Eliminando producto");
        try{
            productRepository.delete(product);
            return convertResponseProduct(product);
        }catch (Exception ex){
            throw new GlobalException(400,Constants.PRODUCTO_DELETE_ERROR);
        }
    }

    @Override
    public List<ResponseProduct> allProduct() {
        try {
            return productRepository.findAll().stream()
                    .map(this::convertResponseProduct)
                    .toList();
        } catch (Exception e) {
            throw new GlobalException(400,Constants.PRODUCT_LIST_ERROR);
        }
    }

    private ResponseProduct registerProduct(ProductEntity productEntity){
        log.info("Guardando datos de producto...");
        try {
            ProductEntity productSave = productRepository.save(productEntity);
            return convertResponseProduct(productSave);
        }catch (Exception ex){
            throw new GlobalException(400,Constants.PRODUCT_ERROR);
        }
    }

    //validaciones para nuevo producto
    private void validateNewProduct(ProductEntity productEntity){
        priceProduct(productEntity.getPrice());
        searchNameProduct(productEntity.getName());
    }

    //validaciones para actualizar productos
    private void validateUpdateProduct(ProductEntity productEntity){
        priceProduct(productEntity.getPrice());
        ProductEntity productExists = searchProduct(productEntity.getId());
        if(productExists.getId() != productEntity.getId()){
            throw new GlobalException(401, Constants.PRODUCT_EXISTS);
        }
    }

    //Buscar si existe el nombre a registrar
    private void searchNameProduct(String name){
        Optional<ProductEntity> productExists = productRepository.findByName(name);
        if (productExists.isPresent()) {
            throw new GlobalException(401, Constants.PRODUCT_EXISTS);
        }
    }

    //Conversiones
    private ResponseProduct convertResponseProduct(ProductEntity productEntity){
        return ResponseProduct.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .category(productEntity.getCategory())
                .build();
    }

    private ProductEntity convertProductEntity(ProductRequest productRequest){
        return ProductEntity.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .build();
    }

    //Validando precio sea mayor a 0
    private void priceProduct(double price){
        if(price<=0){
            throw new GlobalException(400,Constants.PRICE_INVALID);
        }
    }

    //Buscar si existe producto en la base de datos
    private ProductEntity searchProduct(int id){
        ProductEntity product = productRepository.findById(id).orElseThrow(()->new GlobalException(401,Constants.PRODUCT_INVALID));
        return product;
    }
}
