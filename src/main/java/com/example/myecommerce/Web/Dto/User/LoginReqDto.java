package com.example.myecommerce.Web.Dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class LoginReqDto {
    private String username;
    private String password;

    @Builder
    public LoginReqDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
