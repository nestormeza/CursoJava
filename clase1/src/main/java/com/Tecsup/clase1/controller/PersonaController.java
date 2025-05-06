package com.Tecsup.clase1.controller;


import com.Tecsup.clase1.aggregates.response.ReniecResponse;
import com.Tecsup.clase1.aggregates.response.ResponseBase;
import com.Tecsup.clase1.entity.PersonEntity;
import com.Tecsup.clase1.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonaController {
    private final IPersonService personService;

    @GetMapping("/find/{dni}")
    ResponseEntity<ResponseBase<ReniecResponse>> findPerson(@PathVariable String dni){
        return  new ResponseEntity<>(personService.findByDni(dni), HttpStatus.OK);
    }

    @PostMapping("/save/{dni}")
    ResponseEntity<ResponseBase<PersonEntity>> savePerson(@PathVariable String dni ){
        return new ResponseEntity<>(personService.registerPerson(dni),HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseBase<PersonEntity>> updatePerson(@RequestBody PersonEntity personEntity){
        return new ResponseEntity<>(personService.findUpadatePerson(personEntity),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseBase> deletePerson(@PathVariable Long id){
        return new ResponseEntity<>(personService.deletePerson(id),HttpStatus.OK);
    }

    @GetMapping("/list")
    ResponseEntity<ResponseBase<List<PersonEntity>>>listPerson (){
        return new ResponseEntity<>(personService.findPersonActive(),HttpStatus.OK);
    }
}
