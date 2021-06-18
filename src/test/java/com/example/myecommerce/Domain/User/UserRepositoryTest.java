package com.example.myecommerce.Domain.User;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void user_생성및불러오기() {
        String username = "유저 아이디 테스트";
        String password = "유저 비밀번호 테스트";
        String name = "유저이름 테스트";

        userRepository.save(User.builder()
                .username(username)
                .password(password)
                .name(name)
                .role(Role.USER)
                .build());

        List<User> list = userRepository.findAll();

        User test = list.get(0);
        assertThat(test.getName()).isEqualTo(name);
        assertThat(test.getUsername()).isEqualTo(username);
        assertThat(test.getPassword()).isEqualTo(password);
        assertThat(test.getRoleKey()).isEqualTo(Role.USER.getKey());

    }
}
