package com.examen.ms_ordenes.controller;

import com.examen.ms_ordenes.service.OrdenService;
import com.examen.ms_ordenes.utils.constants.Constants;
import com.examen.ms_ordenes.utils.request.RequestOrden;
import com.examen.ms_ordenes.utils.response.ResponseOrden;
import com.examen.ms_ordenes.utils.response.ResponseStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenController {
    private final OrdenService ordenService;

    @GetMapping("/all")
    public ResponseEntity<ResponseStandard<List<ResponseOrden>>> all(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(new ResponseStandard<>(200, Constants.LIST_ORDEN,ordenService.allOrden(token)));
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseStandard<ResponseOrden>> save(@RequestHeader("Authorization") String token, @RequestBody RequestOrden requestOrden){
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.REGISTER_ORDEN,ordenService.saveOrden(requestOrden,token)));
    }

}
