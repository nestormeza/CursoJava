package com.tecsup.examenfinal.controller;

import com.tecsup.examenfinal.service.AuthenticationService;
import com.tecsup.examenfinal.utils.constants.Constants;
import com.tecsup.examenfinal.utils.request.SignInRequest;
import com.tecsup.examenfinal.utils.request.SignUpRequest;
import com.tecsup.examenfinal.utils.response.ResponseStandard;
import com.tecsup.examenfinal.utils.response.SignInResponse;
import com.tecsup.examenfinal.utils.response.UserResponse;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Base64;


@RestController
@RequestMapping("/api/authentication/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

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

    @GetMapping("/clave")
    public ResponseEntity<String> getClave(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String dato = Base64.getEncoder().encodeToString(key.getEncoded());
        return ResponseEntity.ok(dato);
    }
}
