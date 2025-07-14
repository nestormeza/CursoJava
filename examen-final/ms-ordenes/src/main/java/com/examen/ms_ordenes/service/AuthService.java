package com.examen.ms_ordenes.service;

import com.examen.ms_ordenes.utils.response.ResponseValidate;

import java.util.List;

public interface AuthService {
    ResponseValidate validateTokenAuth(String token);
    void getRoles(ResponseValidate data, List<String> roles);
}
