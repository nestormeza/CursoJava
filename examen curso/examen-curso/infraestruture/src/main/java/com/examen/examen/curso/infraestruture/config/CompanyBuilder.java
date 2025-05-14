package com.examen.examen.curso.infraestruture.config;

import com.examen.examen.curso.infraestruture.entity.CompanyEntity;
import com.examen.examen.curso.infraestruture.utils.dto.SunatResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyBuilder {
    public CompanyEntity CompanyEntitySave(SunatResponseDto companySunat) {
        return CompanyEntity.builder().
                name(companySunat.getRazonSocial()).
                document_type(companySunat.getTipoDocumento()).
                document_number(companySunat.getNumeroDocumento()).
                state(companySunat.getCondicion()).
                address(companySunat.getDireccion()).
                status(companySunat.getEstado()).
                ubigeo(companySunat.getUbigeo()).
                track_type(companySunat.getViaTipo()).
                track_name(companySunat.getViaNombre()).
                zone_code(companySunat.getZonaCodigo()).
                zone_type(companySunat.getZonaTipo()).
                number(companySunat.getNumero()).
                interior(companySunat.getInterior()).
                lot(companySunat.getLote()).
                apartment(companySunat.getDpto()).
                block(companySunat.getLote()).
                kilometer(companySunat.getKilometro()).
                district(companySunat.getDistrito()).
                province(companySunat.getProvincia()).
                department(companySunat.getDepartamento()).
                branch_offices(companySunat.getLocalesAnexos()).
                type(companySunat.getTipo()).
                economic_activity(companySunat.getActividadEconomica()).
                worker_count(companySunat.getNumeroTrabajadores()).
                billing_type(companySunat.getTipoFacturacion()).
                accounting_type(companySunat.getTipoContabilidad()).
                foreign_trade(companySunat.getComercioExterior()).
                build();
    }
}
