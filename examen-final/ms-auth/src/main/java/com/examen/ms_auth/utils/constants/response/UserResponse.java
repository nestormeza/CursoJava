package com.examen.ms_auth.utils.constants.response;

import com.examen.ms_auth.entity.RolEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponse {
    private int id;
    private String name;
    private String email;
    private Set<RolEntity> roles = new HashSet<>();
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
}
