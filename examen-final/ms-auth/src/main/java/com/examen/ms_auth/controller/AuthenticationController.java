package com.examen.ms_auth.controller;

import com.examen.ms_auth.service.AuthenticationService;
import com.examen.ms_auth.utils.constants.constans.Constants;
import com.examen.ms_auth.utils.constants.request.SignInRequest;
import com.examen.ms_auth.utils.constants.request.SignUpRequest;
import com.examen.ms_auth.utils.constants.response.ResponseStandard;
import com.examen.ms_auth.utils.constants.response.SignInResponse;
import com.examen.ms_auth.utils.constants.response.UserResponse;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Base64;


@RestController
@RequestMapping("/api/authentication/v1/auth")
@RequiredArgsConstructor
@RefreshScope
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Value("${ms.security}")
    private String gitDato;


    @PostMapping("/register")
    public ResponseEntity<ResponseStandard<UserResponse>> signUpUser(@RequestBody SignUpRequest signUpRequest){
        UserResponse signUpUser = authenticationService.signUpUser(signUpRequest);
        return ResponseEntity.ok( new ResponseStandard<>(200, Constants.SUCCESS_USER,signUpUser));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStandard<SignInResponse>> signInUser(@RequestBody SignInRequest signInRequest){
        SignInResponse signInUser = authenticationService.signIn(signInRequest);
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.SUCCESS_LOGIN,signInUser));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<ResponseStandard<SignInResponse>> refreshToken(@RequestBody String refresh) throws IllegalAccessException{
        SignInResponse signInUser = authenticationService.getTokenByRefreshToken(refresh);
        return ResponseEntity.ok(new ResponseStandard<>(200,Constants.SUCCESS_LOGIN,signInUser));
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable String token){
        return ResponseEntity.ok(authenticationService.validateToken(token));
    }

    @GetMapping("/clave")
    public ResponseEntity<String> getClave(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String dato = Base64.getEncoder().encodeToString(key.getEncoded());
        return ResponseEntity.ok(dato);
    }


    @GetMapping("/git")
    public ResponseEntity<String> prueba(){
        return ResponseEntity.ok(gitDato);
    }
}
