package com.Tecsup.clase1.aggregates.response;

import lombok.*;

@Getter
@Setter
public class ReniecResponse {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreCompleto;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;
}
