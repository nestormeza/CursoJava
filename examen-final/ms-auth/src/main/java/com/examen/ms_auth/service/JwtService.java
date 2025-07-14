package com.examen.ms_auth.service;

import com.examen.ms_auth.entity.UserEntity;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String getUserName(String username);
    String generateToken(UserEntity user);
    boolean validateToken(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> claims, UserDetails userDetails);
    boolean ValidateIsRefreshToken(String token);
    Claims claims(String token);
}
