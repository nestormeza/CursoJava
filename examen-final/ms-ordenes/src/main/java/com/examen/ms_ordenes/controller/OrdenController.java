package com.examen.ms_ordenes.controller;

import com.examen.ms_ordenes.service.OrdenService;
import com.examen.ms_ordenes.utils.response.ResponseOrden;
import com.examen.ms_ordenes.utils.response.ResponseStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenController {
    private final OrdenService ordenService;

    @GetMapping("/all")
    public ResponseEntity<ResponseStandard<List<ResponseOrden>>> all(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(new ResponseStandard<>(200,"Lista de ordenes",ordenService.allOrden(token)));
    }

}
