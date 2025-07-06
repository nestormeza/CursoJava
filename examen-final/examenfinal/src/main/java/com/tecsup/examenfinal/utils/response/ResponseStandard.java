package com.tecsup.examenfinal.utils.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStandard<T> {
    private Integer code;
    private String message;
    private T data;
}
