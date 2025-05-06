package com.Tecsup.clase1.service.impl;

import com.Tecsup.clase1.aggregates.constants.Constants;
import com.Tecsup.clase1.aggregates.response.ReniecResponse;
import com.Tecsup.clase1.aggregates.response.ResponseBase;
import com.Tecsup.clase1.client.IClientReniec;
import com.Tecsup.clase1.entity.PersonEntity;
import com.Tecsup.clase1.exception.ReniecException;
import com.Tecsup.clase1.repository.IPersonRepository;
import com.Tecsup.clase1.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {

    private final IClientReniec clientReniec;
    private final IPersonRepository personRepository;


    @Value("${value.token}")
    private String token;

    @Override
    public ResponseBase<ReniecResponse> findByDni(String dni) {
        ReniecResponse reniecResponse;
        log.info("Buscando informacion para el DNI: {}",dni);
        //ejecutando la consulta a reniec API
        reniecResponse = searchReniecAPI(dni);

        //Preparando respuesta a entregar
        return buildResponse(2004, "Todo ok!!", Optional.of(reniecResponse)
        );
    }

    @Override
    public ResponseBase<PersonEntity> registerPerson(String dni) {
        ReniecResponse reniecResponse;
        log.info("Registrar una persona: {}",dni);
        //Ejecurutar api de busquedad
        reniecResponse=searchReniecAPI(dni);
        //retornar a la vista
        return buildResponse(
                2001,
                "Persona registrado",
                Optional.of(personRepository.save(builPersonEntity(reniecResponse)))
        );
    }

    public ResponseBase deletePerson(Long id) {
        PersonEntity personEntity=searchPersonEntity(id);
        log.info("Eliminar la persona: {}",id);

        personRepository.delete(personEntity);
        return buildResponse(2004, "Persona eliminado", Optional.of(personEntity));
    }

    @Override
    public ResponseBase<PersonEntity> findUpadatePerson(PersonEntity personEntity){
        PersonEntity updatePerson=searchPersonEntity(personEntity.getId());
        updatePerson.setName(personEntity.getName());
        updatePerson.setLastName(personEntity.getLastName());
        updatePerson.setMotherLastName(personEntity.getMotherLastName());
        updatePerson.setFullName(personEntity.getFullName());
        updatePerson.setTypeDocument(personEntity.getTypeDocument());
        updatePerson.setNumberDocument(personEntity.getNumberDocument());
        updatePerson.setCheckDigit(personEntity.getCheckDigit());
        updatePerson.setState(personEntity.getState());
        updatePerson.setDateCreated(personEntity.getDateCreated());
        updatePerson.setUserCreated(personEntity.getUserCreated());
        personRepository.save(updatePerson);

        return buildResponse(2001, "Persona actualizada", Optional.of(updatePerson));
    }

    @Override
    public ResponseBase<List<PersonEntity>> findPersonActive() {
        try {
            List<PersonEntity>  listperson= personRepository.findAllByState(Constants.STATUS_ACTIVE);
            return buildResponse(2004,"Listado de personas registradas",Optional.of(listperson));
        }catch (Exception e){
            throw new ReniecException("Error al obtener listado de personas registradas");
        }
    }


    //ejecuta la llamada a la api
    private ReniecResponse searchReniecAPI(String dni) {
        try {
            log.info("Ejecutando consulta a RENIEC API para el DNI:{}",dni);
            String tokenOk = "Bearer "+token;
            ReniecResponse person = clientReniec.getPerson(dni,tokenOk);
            if(person==null){
                throw new ReniecException("Persona no encontrada en el servidor de reniec :"+dni);
            }
            return person;
        }catch (Exception e){
            throw new ReniecException("Error al consultar a reniec");
        }
    }

    //buscar en la base de datos
    private PersonEntity searchPersonEntity(long id) {
        try {
            log.info("Buscando el persona en la base de datos: {}",id);
            Optional<PersonEntity> personEntity = personRepository.findById(id);
            if(personEntity==null){
                throw new ReniecException("Nose encontrado la persona :"+id);
            }
            return personEntity.get();
        }catch (Exception e){
            throw new ReniecException("Error en acceder a la base de datos");
        }
    }

    //Crear personar entidad
    private PersonEntity builPersonEntity (ReniecResponse reniecResponse) {
        return PersonEntity.builder().
                name(reniecResponse.getNombres()).
                lastName(reniecResponse.getApellidoPaterno()).
                motherLastName(reniecResponse.getApellidoMaterno()).
                fullName(reniecResponse.getNombreCompleto()).
                typeDocument(reniecResponse.getTipoDocumento()).
                numberDocument(reniecResponse.getNumeroDocumento()).
                state(Constants.STATUS_ACTIVE).
                checkDigit(reniecResponse.getDigitoVerificador()).
                dateCreated(new Timestamp(System.currentTimeMillis())).
                userCreated(Constants.USER_ADMIN).build();
    }

    //plantilla de retorno de respuesta
    private <T> ResponseBase<T> buildResponse(int code, String message, Optional<T> optional) {
        ResponseBase<T> responseBase = new ResponseBase<>();
        responseBase.setCode(code);
        responseBase.setMessage(message);
        responseBase.setEntity(optional);
        return responseBase;
    }
}
