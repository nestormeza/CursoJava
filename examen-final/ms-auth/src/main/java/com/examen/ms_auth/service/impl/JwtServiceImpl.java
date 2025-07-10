package com.examen.ms_auth.service.impl;

import com.examen.ms_auth.entity.UserEntity;
import com.examen.ms_auth.service.JwtService;
import com.examen.ms_auth.utils.constants.constans.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Component
public class JwtServiceImpl implements JwtService {
    @Value("${key.signature}")
    private String keySignature;

    @Override
    public String getUserName(String token) {
        return Claim(token,Claims::getSubject);
    }

    @Override
    public String generateToken(UserEntity user) {
        log.info("Generando token....");
        Map<String,Object> claims= AllClaimsPersonalization(user);
        claims.put("type", Constants.ACCESS);
        claims.put("id",user.getId());
        claims.put("name",user.getName());
        claims.put("email", user.getEmail());

        //tiempo de vide del token
        Date dateCreation = new Date();
        Date dateExpiration = new Date(dateCreation.getTime() + 6000000);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(dateCreation)
                .setExpiration(dateExpiration)
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username= getUserName(token);
        log.info("Validando token...");
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claim("type", Constants.REFRESH)
                .setClaims(claims != null ? claims : new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+600000))
                .signWith(getKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean ValidateIsRefreshToken(String token) {
        //validar access token
        Claims claims = AllClaims(token);
        String typeToken = claims.get("type",String.class);
        return Constants.REFRESH.equalsIgnoreCase(typeToken);
    }

    //decodificar clave
    private Key getKey(){
        byte[] key = Decoders.BASE64.decode(keySignature);
        //retorna la clave de tipo key
        return Keys.hmacShaKeyFor(key);
    }

    //Retornar todos los claims
    private Claims AllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Retornar el claim especifico
    private <T> T Claim(String token, Function<Claims, T> claimsTFunction){
        return  claimsTFunction.apply(AllClaims(token));
    }

    //Token Expirado
    private boolean isTokenExpired(String token){
        log.info("Verificando si el token a expirado");
        return Claim(token, Claims::getExpiration).before(new Date());
    }

    //Personalización privada del token a enviar
    private Map<String, Object> AllClaimsPersonalization(UserEntity user){
        Map<String, Object> claimsPersonalization = new HashMap<>();
        claimsPersonalization.put("accountNonExpired",user.isAccountNonExpired());
        claimsPersonalization.put("accountNonLocked", user.isAccountNonLocked());
        claimsPersonalization.put("credentialsNonExpired",user.isCredentialsNonExpired());
        claimsPersonalization.put("enabled", user.isEnabled());
        claimsPersonalization.put("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return claimsPersonalization;

    }
}
