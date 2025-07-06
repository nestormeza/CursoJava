package com.tecsup.examenfinal.service;

import com.tecsup.examenfinal.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String getUserName(String username);
    String generateToken(UserEntity user);
    boolean validateToken(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> claims, UserDetails userDetails);
    boolean ValidateIsRefreshToken(String token);
}
