package com.example.myecommerce.Web.Dto.User;

import com.example.myecommerce.Domain.User.User;
import lombok.Getter;

@Getter
public class UserResDto {
    private String username;
    private String name;
    private String email;
    private String address;

    public UserResDto(User entity) {
        this.username = entity.getUsername();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.address = entity.getAddress();
    }
}
