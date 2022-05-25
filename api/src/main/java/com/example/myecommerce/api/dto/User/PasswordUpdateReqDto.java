package com.example.myecommerce.api.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@NoArgsConstructor
@ToString
public class PasswordUpdateReqDto {
    String currentPassword;
    String updatedPassword;

    @Builder
    public PasswordUpdateReqDto(String currentPassword, String updatedPassword) {
        this.currentPassword = currentPassword;
        this.updatedPassword = updatedPassword;
    }
}
