package com.example.myecommerce.Web.Dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserUpdateReqDto {
    private String name;
    private String email;
    private String address;

    @Builder
    public UserUpdateReqDto(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
