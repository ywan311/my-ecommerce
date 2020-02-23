package com.example.myecommerce.Web;
import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Comment.CommentRepository;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import com.example.myecommerce.Web.Dto.Product.ProductSaveReqDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProductApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    @After
    public void tearDown() throws Exception{
        productRepository.deleteAll();
    }

    @Test
    public void Product_등록테스트(){
        //given
        String title = "상품명 테스트";
        String content = "상품내용 테스트";
        int price = 1515;

        ProductSaveReqDto dto = ProductSaveReqDto.builder()
                .title(title)
                .content(content)
                .price(price)
                .build();

        String url  = "http://localhost:"+port+"/api/v1/product";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,dto,Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Product> list = productRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);
        assertThat(list.get(0).getPrice()).isEqualTo(price);
        assertThat(list.get(0).getComments().isEmpty()).isTrue();
    }
    @Test
    public void comment_등록테스트(){
        //given
        String title = "상품명 테스트";
        String content = "상품내용 테스트";
        int price = 1515;

        String cTitle ="댓글명 테스트";
        String cContent ="댓글내용 테스트";

        Product testP =productRepository.save(Product.builder()
                .title(title)
                .content(content)
                .price(price)
                .build());

        CommentReqDto commentDto = CommentReqDto.builder()
                .title(cTitle)
                .content(cContent)
                .build();

        String url  = "http://localhost:"+port+"/api/v1/product/"+testP.getId();

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,commentDto,Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Product> list = productRepository.findAll();
        Comment test  = commentRepository.findAll().get(0);
        assertThat(list.get(0).getComments().contains(test)).isTrue();
    }


}
