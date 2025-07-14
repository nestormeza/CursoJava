package com.examen.ms_auth.utils.constants.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseValidate {
    private Boolean state;
    private List<String> roles;
}
