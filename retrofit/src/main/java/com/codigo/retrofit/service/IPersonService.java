package com.codigo.retrofit.service;

import com.codigo.retrofit.aggregates.response.ReniecResponse;
import com.codigo.retrofit.aggregates.response.ResponseBase;
import com.codigo.retrofit.entity.PersonEntity;

import java.io.IOException;

public interface IPersonService {
    ReniecResponse findByDni(String dni);
    ResponseBase<PersonEntity> savePerson(String dni);
    ResponseBase<PersonEntity> updatePerson(PersonEntity personEntity,long id) ;
    ResponseBase<PersonEntity> deletePerson(long id);
    ResponseBase<PersonEntity> findById(long id) ;
}
