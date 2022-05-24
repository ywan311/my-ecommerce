package com.example.myecommerce.ecommercecore.Domain.Product;

import com.example.myecommerce.Domain.User.Role;
import com.example.myecommerce.Domain.User.User;
import com.example.myecommerce.Domain.User.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    private User user;

    @Before
    public void setUp(){
        String username = "유저 아이디 테스트";
        String password = "유저 비밀번호 테스트";
        String name = "유저이름 테스트";

        user = userRepository.save(User.builder()
                .username(username)
                .password(password)
                .name(name)
                .role(Role.USER)
                .build());
    }

    @After
    public void cleaup() {
        productRepository.deleteAll();
    }

    @Test
    public void 상품저장_불러오기() {
        //given
        String title = "상품명 테스트";
        String content = "상품 내용 테스트";
        int price = 10000;

        productRepository.save(
                Product.builder()
                        .title(title)
                        .content(content)
                        .price(price)
                        .user(user)
                        .build());

        //when
        List<Product> products = productRepository.findAll();

        //then
        Product product = products.get(0);
        Assertions.assertThat(product.getTitle()).isEqualTo(title);
        Assertions.assertThat(product.getContent()).isEqualTo(content);
        Assertions.assertThat(product.getPrice()).isEqualTo(price);
    }

    @Test
    public void BaseTimeEntity시간테스트() {
        //given

        String title = "상품명 테스트";
        String content = "상품 내용 테스트";
        int price = 10000;
        LocalDateTime now = LocalDateTime.of(2020, 2, 21, 5, 34, 00);

        productRepository.save(
                Product.builder()
                        .title(title)
                        .content(content)
                        .price(price)
                        .user(user)
                        .build());

        //when
        List<Product> products = productRepository.findAll();

        //then
        Product product = products.get(0);
        Assertions.assertThat(product.getCreatedDate()).isAfterOrEqualTo(now);
        Assertions.assertThat(product.getModifiedDate()).isAfterOrEqualTo(now);

    }
}
