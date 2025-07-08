package com.examen.ms_auth.utils.constants.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStandard<T> {
    private Integer code;
    private String message;
    private T data;
}
