package com.codigo.retrofit.service.impl;

import com.codigo.retrofit.aggregates.constants.Constants;
import com.codigo.retrofit.aggregates.response.ReniecResponse;
import com.codigo.retrofit.aggregates.response.ResponseBase;
import com.codigo.retrofit.entity.PersonEntity;
import com.codigo.retrofit.exception.ConsultReniecException;
import com.codigo.retrofit.repository.PersonRepository;
import com.codigo.retrofit.retrofit.ClientRetroFit;
import com.codigo.retrofit.retrofit.IRetroFitSerive;
import com.codigo.retrofit.service.IPersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;

    IRetroFitSerive configRetroFit = ClientRetroFit.getRetrofit().create(IRetroFitSerive.class);

    @Value("${value.token}")
    private String token;

    //buscar dni en el api de reniec
    @Override
    public ReniecResponse findByDni(String dni) {
        return searchPersonByDni(dni);
    }

    //buscar una persona en la base de datos
    @Override
    public ResponseBase<PersonEntity> findById(long id) {
        PersonEntity person = searchDataBase(id);

        return buildResponse(2001, "todo OK!!", Optional.of(person));
    }

    //Guardar una persona en la base de datos
    @Override
    public ResponseBase<PersonEntity> savePerson(String dni) {
        ReniecResponse personSearch = searchPersonByDni(dni);

        PersonEntity person = person(personSearch);

        log.info("Guardando persona en la base de datos");
        PersonEntity personSave = personRepository.save(person);

        return buildResponse(2001, "Todo OK!!", Optional.of(personSave));
    }

    //actualizar una persona
    @Override
    public ResponseBase<PersonEntity> updatePerson(PersonEntity personEntity, long id) {
        PersonEntity person=searchDataBase(id);

        log.info("Actualizando persona en la base de datos");
        PersonEntity personSave = personRepository.save(personEntity);
        return buildResponse(2001, "todo OK!!", Optional.of(personSave));
    }

    //eliminar una persona
    @Override
    public ResponseBase<PersonEntity> deletePerson(long id){
        PersonEntity person = searchDataBase(id);

        log.info("Eliminando persona en la base de datos");
        personRepository.delete(person);

        return buildResponse(2001, "todo OK!!", Optional.of(person));
    }

    //busquedad de base de datos
    private PersonEntity searchDataBase(long id) {
        try {
            log.info("Buscando persona en la base de datos");
            return personRepository.findById(id).orElseThrow(()-> new ConsultReniecException("Persona no encontrada!!"));
        }catch (Exception e){
            throw new ConsultReniecException("Error al buscar persona en la base de datos!!",e);
        }
    }

    //llamar a api
    private ReniecResponse searchPersonByDni(String dni) {
        try {
            log.info("Iniciando busquedad de persona por dni " + dni);
            Response<ReniecResponse> response = configRetroFit.getReniecDni("Bearer " + token, dni).execute();
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                log.info("Persona encontrada " + dni);
                return response.body();
            }else{
                throw new ConsultReniecException("Dni no valido"+dni);
            }
        }catch (Exception e){
            throw new ConsultReniecException("Error del servidor de reniec al consultar:"+dni,e);
        }
    }

    //dar fomarto de retorno de api
    private PersonEntity person(ReniecResponse reniecResponse) {
        if(reniecResponse==null){
            throw new ConsultReniecException("Error en la respuesta del servidor reniec");
        }
        return PersonEntity.builder()
                .name(reniecResponse.getNombres())
                .fullName(reniecResponse.getNombreCompleto())
                .lastName(reniecResponse.getApellidoPaterno())
                .motherLastName(reniecResponse.getApellidoMaterno())
                .typeDocument(reniecResponse.getTipoDocumento())
                .numberDocument(reniecResponse.getNumeroDocumento())
                .checkDigit(reniecResponse.getDigitoVerificador())
                .state(Constants.STATUS_ACTIVE)
                .userCreated(Constants.USER_ADMIN)
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    private <T> ResponseBase<T> buildResponse(
            int code, String message, Optional<T> optional) {
        ResponseBase<T> responseBase = new ResponseBase<>();
        responseBase.setCode(code);
        responseBase.setMessage(message);
        responseBase.setEntity(optional);
        return responseBase;
    }
}
