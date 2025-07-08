package com.examen.ms_auth.utils.constants.constans;

public class Constants {
    public static final Boolean STATUS_ACTIVE = true;

    //TOKEN
    public static final String REFRESH = "refresh";
    public static final String ACCESS = "access";
    public static final String ENDPOINTS_PERMIT = "/api/authentication/v1/**";
    public static final String ENDPOINTS_USER= "/api/user/v1/**";
    public static final String ENDPOINTS_ADMIN = "/api/admin/v1/**";
    public static final String ENDPOINTS_SUPERADMIN = "/api/superadmin/v1/**";

    //MESSAGE API
    public static final String SUCCESS_USER= "Usuario registrando correctamente";
    public static final String SUCCESS_LOGIN = "Usuario logeado correctamente";

    //MESSAGE LOG INFO
    public static final String REGISTER_USER="Registrando usuario";

    //MESSAGE EXCEPTION
    public static final String USER_404 = "Usuario no encontrado en la base de datos";
    public static final String TOKEN_REFRESH_400 = "Token ingresado no es ningún refresh";
    public static final String ROL_404 = "El rol no existe en la base datos";
    public static final String TOKEN_400 = "Token ingresado no es valido";
    public static final String EMAIL_EXISTS_401 = "EL email ya existe en la base de datos";


    public static final String ENDPOINTS_ACTUATOR = "/actuator/refresh";
    public static final String ENDPOINTS_ACTUATOR_BUS = "/actuator/busrefresh";
    public static final String[] PERMIT_ENDPOINTS = {
            "/api/authentication/v1/**",
            "/actuator/**"
    };




}
