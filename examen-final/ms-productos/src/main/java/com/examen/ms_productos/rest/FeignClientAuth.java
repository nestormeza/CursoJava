package com.examen.ms_productos.rest;

import com.examen.ms_productos.utils.response.ResponseValidate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "client-auth",url = "http://localhost:8080/apis/examen/api/authentication/v1/auth")
public interface FeignClientAuth {
    @PostMapping("/validate/{token}")
    ResponseValidate getvalidateToken(
            @PathVariable("token") String token
    );
}
