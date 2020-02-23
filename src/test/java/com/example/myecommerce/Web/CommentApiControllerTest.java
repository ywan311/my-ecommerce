package com.example.myecommerce.Web;

import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CommentApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    private Long pId;

    @Before
    public void makeProduct(){
        String pTitle = "상품명 테스트";
        String pContent = "상품내용 테스트";
        int price = 1515;

        Product product = productRepository.save(Product.builder()
                .title(pTitle)
                .content(pContent)
                .price(price)
                .build());

        pId = product.getId();

    }

    @After
    public void cleanUp(){
        productRepository.deleteAll();
    }


}
