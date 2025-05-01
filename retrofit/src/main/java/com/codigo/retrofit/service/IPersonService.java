package com.codigo.retrofit.service;

import com.codigo.retrofit.aggregates.response.ReniecResponse;
import com.codigo.retrofit.aggregates.response.ResponseBase;
import com.codigo.retrofit.entity.PersonEntity;

import java.io.IOException;

public interface IPersonService {
    ReniecResponse findByDni(String dni) throws IOException;
    ResponseBase<PersonEntity> savePerson(String dni) throws IOException;
    ResponseBase<PersonEntity> updatePerson(PersonEntity personEntity,long id) throws IOException;
    ResponseBase<PersonEntity> deletePerson(long id) throws IOException;
    ResponseBase<PersonEntity> findById(long id) throws IOException;
}
