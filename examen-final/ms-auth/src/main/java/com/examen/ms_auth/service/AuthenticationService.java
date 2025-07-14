package com.examen.ms_auth.service;

import com.examen.ms_auth.utils.constants.request.SignInRequest;
import com.examen.ms_auth.utils.constants.request.SignUpRequest;
import com.examen.ms_auth.utils.constants.response.ResponseValidate;
import com.examen.ms_auth.utils.constants.response.SignInResponse;
import com.examen.ms_auth.utils.constants.response.UserResponse;


public interface AuthenticationService {
    UserResponse signUpUser(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);
    SignInResponse getTokenByRefreshToken(String token) throws IllegalAccessException;
    ResponseValidate validateToken(String token);

}
