package com.spring.tming.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginReq {
    private String email;
    private String password;

    @Builder
    private LoginReq(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
