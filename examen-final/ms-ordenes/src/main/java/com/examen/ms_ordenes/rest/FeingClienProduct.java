package com.examen.ms_ordenes.rest;

import com.examen.ms_ordenes.utils.response.ResponseProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "client-product",url = "${api-product}")
public interface FeingClienProduct {
    @PostMapping("/find/{id}")
    ResponseProduct getvalidateProduct(
            @PathVariable("id") int id,
            @RequestHeader("Authorization") String token
    );
}
