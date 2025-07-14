package com.examen.ms_productos.controller;

import com.examen.ms_productos.entity.ProductEntity;
import com.examen.ms_productos.service.ProductService;
import com.examen.ms_productos.utils.constants.Constants;
import com.examen.ms_productos.utils.request.ProductRequest;
import com.examen.ms_productos.utils.response.ResponseProduct;
import com.examen.ms_productos.utils.response.ResponseStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ResponseStandard<ResponseProduct>> save(@RequestHeader("Authorization") String token,@RequestBody ProductRequest productRequest){
        ResponseProduct productNew = productService.saveProduct(token,productRequest);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_SUCCESS,productNew));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStandard<ResponseProduct>> update(@RequestHeader("Authorization") String token,@RequestBody ProductEntity productEntity){
        ResponseProduct productUpdate = productService.updateProduct(token,productEntity);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_UPDATE,productUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStandard<ResponseProduct>> delete(@RequestHeader("Authorization") String token,@PathVariable int id){
        ResponseProduct productUpdate = productService.deleteProduct(token,id);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_DELETE,null));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStandard<List<ResponseProduct>>> all(@RequestHeader("Authorization") String token){
        List<ResponseProduct> products= productService.allProduct(token);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_LIST,products));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductEntity> product(@RequestHeader("Authorization") String token,@PathVariable int id){
        ProductEntity product = productService.validateProduct(id,token);
        return ResponseEntity.ok(product);
    }
}
