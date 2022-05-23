package com.example.myecommerce.Domain.Comment;

import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Domain.User.Role;
import com.example.myecommerce.Domain.User.User;
import com.example.myecommerce.Domain.User.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

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
        commentRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 댓글저장_불러오기() {
        //given
        String commentTitle = "댓글명 테스트";
        String commentContent = "댓글 테스트";
        String prodTitle = "상품명 테스트";
        String prodContent = "상품 내용 테스트";
        int prodPrice = 10000;


        Product prod = productRepository.save(
                Product.builder()
                        .title(prodTitle)
                        .content(prodContent)
                        .price(prodPrice)
                        .user(user)
                        .build());

        commentRepository.save(
                Comment.builder()
                        .title(commentTitle)
                        .product(prod)
                        .user(user)
                        .content(commentContent)
                        .build());

        //when
        List<Comment> comments = commentRepository.findAll();

        //then
        Comment test = comments.get(0);
        assertThat(test.getProduct().getId()).isEqualTo(prod.getId());
        assertThat(test.getTitle()).isEqualTo(commentTitle);
        assertThat(test.getContent()).isEqualTo(commentContent);

    }
}
