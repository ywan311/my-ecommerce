package com.example.myecommerce.Domain.Comment;

import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ProductRepository productRepository;

    @After
    public void cleaup(){
        commentRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void 댓글저장_불러오기(){
        //given
        String commentContent ="댓글 테스트";
        String prodTitle ="상품명 테스트";
        String prodContent ="상품 내용 테스트";
        int prodPrice = 10000;

        Product prod = productRepository.save(
                Product.builder()
                        .title(prodTitle)
                        .content(prodContent)
                        .price(prodPrice)
                        .build());

       commentRepository.save(
                Comment.builder()
                .product(prod)
                .content(commentContent)
                .build());

        //when
        List<Comment> comments = commentRepository.findAll();

        //then
        Comment test = comments.get(0);
        assertThat(test.getProduct().getId()).isEqualTo(prod.getId());
        assertThat(test.getContent()).isEqualTo(commentContent);

    }
}
