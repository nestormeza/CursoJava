package com.codigo.retrofit.controller;

import com.codigo.retrofit.aggregates.response.ReniecResponse;
import com.codigo.retrofit.aggregates.response.ResponseBase;
import com.codigo.retrofit.entity.PersonEntity;
import com.codigo.retrofit.service.IPersonService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/person/")
@RequiredArgsConstructor
public class PersonController {
    private final IPersonService personService;

    @GetMapping("/find/reniec/{dni}")
    public ResponseEntity<ReniecResponse> searchReniec(@PathVariable String dni) throws IOException {
        return new ResponseEntity<>(personService.findByDni(dni), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseBase<PersonEntity>> findById(@PathVariable long id) throws IOException {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save/{dni}")
    public ResponseEntity<ResponseBase<PersonEntity>> savePerson(@PathVariable String dni) throws IOException {
        return new ResponseEntity<>(personService.savePerson(dni),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBase<PersonEntity>> updatePersonById(@RequestBody PersonEntity personEntity, @PathVariable long id) throws IOException {
        return new ResponseEntity<>(personService.updatePerson(personEntity,id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBase<PersonEntity>> deletePersonById(@PathVariable long id) throws IOException {
        return new ResponseEntity<>(personService.deletePerson(id),HttpStatus.OK);
    }
}
