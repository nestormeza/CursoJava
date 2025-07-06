package com.tecsup.examenfinal.utils.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInResponse {
    private String accessToken;
    private String refreshToken;
}
