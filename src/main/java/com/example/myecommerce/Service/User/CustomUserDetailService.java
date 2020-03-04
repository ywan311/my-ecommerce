package com.example.myecommerce.Service.User;

import com.example.myecommerce.Domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
    public UserDetails loadUserById(String id) throws UserPrincipalNotFoundException {
        return userRepository.findById(Long.valueOf(id)).orElseThrow(()->new UserPrincipalNotFoundException("회원이 없습니다."));
    }
}
