package com.example.myecommerce.Service.User;

import com.example.myecommerce.Domain.User.Role;
import com.example.myecommerce.Domain.User.User;
import com.example.myecommerce.Domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean checkLogin(String username, String password){
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(password,user.getPassword());
    }

    public boolean register(String username,String password, String name) {
        if(userRepository.findByUsername(username)!=null)return false;
        Long id = userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .role(Role.USER)
                .build()).getId();
        return id!=null?true:false;
    }
}
