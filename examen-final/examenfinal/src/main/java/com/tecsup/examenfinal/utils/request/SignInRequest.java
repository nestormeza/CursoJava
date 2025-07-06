package com.tecsup.examenfinal.utils.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private String email;
    private String password;
}
