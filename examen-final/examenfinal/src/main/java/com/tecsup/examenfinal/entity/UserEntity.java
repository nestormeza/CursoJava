package com.tecsup.examenfinal.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //obtener roles
        return roles.stream().map(rol->new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}