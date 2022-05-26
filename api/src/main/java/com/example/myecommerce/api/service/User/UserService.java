package com.example.myecommerce.api.service.User;

import com.example.myecommerce.api.dto.User.PasswordUpdateReqDto;
import com.example.myecommerce.api.dto.User.UserRegisterReqDto;
import com.example.myecommerce.api.dto.User.UserUpdateReqDto;
import com.example.myecoomerce.myecommercecore.User.Role;
import com.example.myecoomerce.myecommercecore.User.User;
import com.example.myecoomerce.myecommercecore.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean checkLogin(String username, String password){
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(password,user.getPassword());
    }

    public boolean register(UserRegisterReqDto dto) {
        if(userRepository.findByUsername(dto.getUsername())!=null)return false;
        Long id = userRepository.save(User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .role(Role.USER)
                .build()).getId();
        return id != null;
    }
    @Transactional
    public Long update(String username, UserUpdateReqDto dto){
        User user = userRepository.findByUsername(username);
        user.update(dto.getName(),dto.getEmail(),dto.getAddress());
        return user.getId();
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("회원이 없습니다."+id));
        userRepository.delete(user);
    }

    @Transactional
    public Long updatePassword(PasswordUpdateReqDto dto){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!passwordEncoder.matches(user.getPassword(),dto.getCurrentPassword()))return null;
        else{
            user.updatePassword(passwordEncoder.encode(dto.getUpdatedPassword()));
            return user.getId();
        }

    }
}
