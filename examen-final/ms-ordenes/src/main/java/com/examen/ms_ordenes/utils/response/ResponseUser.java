package com.examen.ms_ordenes.utils.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseUser {
    private int id;
    private String name;
}
