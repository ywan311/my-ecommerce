package com.example.myecommerce.Web.Dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserRegisterReqDto {
    private String username;
    private String password;
    private String name;

    @Builder
    public UserRegisterReqDto(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
