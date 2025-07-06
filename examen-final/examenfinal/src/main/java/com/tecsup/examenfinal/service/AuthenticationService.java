package com.tecsup.examenfinal.service;

import com.tecsup.examenfinal.utils.request.SignInRequest;
import com.tecsup.examenfinal.utils.request.SignUpRequest;
import com.tecsup.examenfinal.utils.response.SignInResponse;
import com.tecsup.examenfinal.utils.response.UserResponse;

import java.util.List;


public interface AuthenticationService {
    UserResponse signUpUser(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);
    SignInResponse getTokenByRefreshToken(String token) throws IllegalAccessException;
}
