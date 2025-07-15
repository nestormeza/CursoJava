package com.examen.ms_ordenes.rest;

import com.examen.ms_ordenes.utils.response.ResponseValidate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "client-auth",url = "${api-auth}")
public interface FeingClientAuth {
    @PostMapping("/validate/{token}")
    ResponseValidate getvalidateToken(
            @PathVariable("token") String token
    );
}
