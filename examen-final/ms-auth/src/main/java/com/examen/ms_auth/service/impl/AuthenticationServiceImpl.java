package com.examen.ms_auth.service.impl;

import com.examen.ms_auth.entity.RolEntity;
import com.examen.ms_auth.entity.UserEntity;
import com.examen.ms_auth.exception.GlobalException;
import com.examen.ms_auth.repository.RolRepository;
import com.examen.ms_auth.repository.UserRepository;
import com.examen.ms_auth.service.AuthenticationService;
import com.examen.ms_auth.service.JwtService;
import com.examen.ms_auth.service.UserService;
import com.examen.ms_auth.utils.constants.constans.Constants;
import com.examen.ms_auth.utils.constants.constans.Role;
import com.examen.ms_auth.utils.constants.request.SignInRequest;
import com.examen.ms_auth.utils.constants.request.SignUpRequest;
import com.examen.ms_auth.utils.constants.response.SignInResponse;
import com.examen.ms_auth.utils.constants.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public UserResponse signUpUser(SignUpRequest signUpRequest) {
        return userRegister(signUpRequest);
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        log.info("Buscando usuario...");
        UserEntity user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                ()->new GlobalException(404, Constants.USER_404)
        );
        //Generar tokens
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        return SignInResponse.builder().accessToken(token).refreshToken(refreshToken).build();
    }

    @Override
    public SignInResponse getTokenByRefreshToken(String token) throws IllegalAccessException {
        if(!jwtService.ValidateIsRefreshToken(token)){
            throw  new GlobalException(400,Constants.TOKEN_REFRESH_400);
        }
        String username = jwtService.getUserName(token);
        UserEntity user = userRepository.findByEmail(username).orElseThrow(
                ()->new GlobalException(404,Constants.USER_404)
        );
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(user.getUsername());
        if(!jwtService.validateToken(token,userDetails)){
            throw  new GlobalException(400, Constants.TOKEN_400);
        }
        String newToken = jwtService.generateToken(user);
        return SignInResponse.builder().accessToken(newToken).refreshToken(token).build();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            String usernameToken = jwtService.getUserName(token);
            UserDetails userdetails = userService.userDetailsService().loadUserByUsername(usernameToken);

            boolean result = jwtService.validateToken(token,userdetails) && !jwtService.ValidateIsRefreshToken(token);

            return  result;
        }catch (Exception ex){
            throw new GlobalException(400,"El token no es valido");
        }
    }

    private UserEntity convertUserEntity(SignUpRequest signUpRequest){
        return UserEntity.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()))
                .isAccountNonExpired(Constants.STATUS_ACTIVE)
                .isAccountNonLocked(Constants.STATUS_ACTIVE)
                .isCredentialsNonExpired(Constants.STATUS_ACTIVE)
                .isEnabled(Constants.STATUS_ACTIVE)
                .build();
    }

    private UserResponse convertUserResponse(UserEntity userEntity){
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .accountNonExpired(userEntity.getIsAccountNonExpired())
                .accountNonLocked(userEntity.getIsAccountNonLocked())
                .credentialsNonExpired(userEntity.isCredentialsNonExpired())
                .enabled(userEntity.getIsEnabled())
                .build();
    }

    private RolEntity getRoles(Role rol){
        return rolRepository.findByName(rol.name()).orElseThrow(
                ()->new GlobalException(404,Constants.ROL_404)
        );
    }

    private void searchUser(String email){
        Optional<UserEntity> userExists= userRepository.findByEmail(email);
        if (userExists.isPresent()) {
            throw new GlobalException(401,Constants.EMAIL_EXISTS_401);
        }
    }

    private UserResponse userRegister(SignUpRequest signUpRequest){
        searchUser(signUpRequest.getEmail());
        Set<RolEntity> roles =  assignedRoles(signUpRequest.getRoles());

        log.info(Constants.REGISTER_USER);
        UserEntity userSave = convertUserEntity(signUpRequest);
        userSave.setRoles(roles);
        userRepository.save(userSave);

        return convertUserResponse(userSave);
    }

    private Set<RolEntity> assignedRoles(Set<String> rolesRequest){
        Set<RolEntity> roles = new HashSet<>();

        for (String roleName : rolesRequest){
            try {
                Role roleEnum = Role.valueOf(roleName.toUpperCase());
                roles.add(getRoles(roleEnum));
            } catch (IllegalArgumentException e) {
                throw new GlobalException(404,Constants.ROL_404);
            }
        }
        return roles;
    }
}
