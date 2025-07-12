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
    public ResponseEntity<ResponseStandard<ResponseProduct>> save(@RequestBody ProductRequest productRequest){
        ResponseProduct productNew = productService.saveProduct(productRequest);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_SUCCESS,productNew));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStandard<ResponseProduct>> update(@RequestBody ProductEntity productEntity){
        ResponseProduct productUpdate = productService.updateProduct(productEntity);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_UPDATE,productUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStandard<ResponseProduct>> delete(@PathVariable int id){
        ResponseProduct productUpdate = productService.deleteProduct(id);
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_DELETE,null));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStandard<List<ResponseProduct>>> all(){
        List<ResponseProduct> products= productService.allProduct();
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.PRODUCT_LIST,products));
    }
}
