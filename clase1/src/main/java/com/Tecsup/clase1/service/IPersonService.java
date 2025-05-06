package com.Tecsup.clase1.service;

import com.Tecsup.clase1.aggregates.response.ReniecResponse;
import com.Tecsup.clase1.aggregates.response.ResponseBase;
import com.Tecsup.clase1.entity.PersonEntity;

import java.util.List;

public interface IPersonService {
    ResponseBase<ReniecResponse> findByDni(String dni);
    ResponseBase<PersonEntity> registerPerson(String dni);
    ResponseBase<List<PersonEntity>> findPersonActive();
    ResponseBase<PersonEntity> findUpadatePerson(PersonEntity personEntity);
    ResponseBase deletePerson(Long id);

}
