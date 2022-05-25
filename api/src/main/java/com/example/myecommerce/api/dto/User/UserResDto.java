package com.example.myecommerce.api.dto.User;

import com.example.myecoomerce.myecommercecore.Domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
