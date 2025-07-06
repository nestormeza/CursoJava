package com.tecsup.examenfinal.utils.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;
}
