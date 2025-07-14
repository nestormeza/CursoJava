package com.examen.ms_productos.service.impl;

import com.examen.ms_productos.config.FeignClientApi;
import com.examen.ms_productos.entity.ProductEntity;
import com.examen.ms_productos.exception.GlobalException;
import com.examen.ms_productos.repository.ProductRepository;
import com.examen.ms_productos.service.ProductService;
import com.examen.ms_productos.utils.constants.Constants;
import com.examen.ms_productos.utils.request.ProductRequest;
import com.examen.ms_productos.utils.response.ResponseProduct;
import com.examen.ms_productos.utils.response.ResponseValidate;
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
    private final FeignClientApi feignClientApi;

    @Override
    public ResponseProduct saveProduct(String token,ProductRequest productRequest) {
        ResponseValidate validate= validateToken(token);
        // Validar que tenga el rol ADMIN
        validateRole(validate,List.of("ADMIN","SUPERADMIN"));
        try {
            validateNewProduct(convertProductEntity(productRequest));
            ProductEntity productNew = convertProductEntity(productRequest);
            return registerProduct(productNew);
        }catch (Exception e){
            throw new GlobalException(500,"Error al registrar el producto");
        }

    }

    @Override
    public ResponseProduct updateProduct(String token,ProductEntity productEntity) {
        ResponseValidate validate= validateToken(token);
        // Validar que tenga el rol ADMIN
        validateRole(validate,List.of("ADMIN","SUPERADMIN"));
        try {
            validateUpdateProduct(productEntity);
            return registerProduct(productEntity);
        }catch (Exception e){
            throw new GlobalException(500,"Error al actualizar el producto");
        }
    }

    @Override
    public ResponseProduct deleteProduct(String token,int id) {
        ResponseValidate validate= validateToken(token);
        // Validar que tenga el rol ADMIN
        validateRole(validate,List.of("ADMIN","SUPERADMIN"));
        try{
            ProductEntity product = searchProduct(id);
            log.info("Eliminando producto");
            productRepository.delete(product);
            return convertResponseProduct(product);
        }catch (Exception ex){
            throw new GlobalException(400,Constants.PRODUCTO_DELETE_ERROR);
        }
    }

    @Override
    public List<ResponseProduct> allProduct(String token) {
        ResponseValidate validate= validateToken(token);
        // Validar que tenga el rol ADMIN
        validateRole(validate,List.of("ADMIN","SUPERADMIN"));
        try {
            return productRepository.findAll().stream()
                    .map(this::convertResponseProduct)
                    .toList();
        }catch (Exception e){
            throw new GlobalException(500,"Error al listar los productos");
        }
    }

    @Override
    public ProductEntity validateProduct(int id,String token) {
        ResponseValidate validate= validateToken(token);
        // Validar que tenga el rol ADMIN
        validateRole(validate,List.of("USER","ADMIN","SUPERADMIN"));
        try{
            ProductEntity product = searchProduct(id);
            return product;
        }catch (Exception e){
            throw new GlobalException(500, "Error al obtener producto");
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

    //validar token y obtener roles
    private ResponseValidate validateToken(String token){
        try {
            // Quitar "Bearer "
            String convertToken = token.replace("Bearer ", "");

            ResponseValidate result = feignClientApi.validateToken(convertToken);

            if(!result.getState()){
                throw new GlobalException(403,"El token no es valido");
            }
            return result;
        }catch (Exception e){
            log.error("Error al validar token", e);
            throw new GlobalException(400,"ERROR");
        }

    }

    //validar el rol de usuario
    private void validateRole(ResponseValidate data, List<String> roles) {
        boolean hasRole = data.getRoles().stream().anyMatch(roles::contains);
        if (!hasRole) {
            throw new GlobalException(403,"No tienes los permisos para acceder");
        }
    }

}
