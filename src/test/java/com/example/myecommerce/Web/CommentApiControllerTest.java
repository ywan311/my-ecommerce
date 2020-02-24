package com.example.myecommerce.Web;

import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Comment.CommentRepository;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CommentApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Long pId;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

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

    @Test
    public void 댓글등록() throws Exception{
        String title = "댓글등록 테스트";
        String content = "댓글 내용";

        CommentReqDto dto = CommentReqDto.builder()
                .title(title)
                .content(content)
                .build();

        String url  = "http://localhost:"+port+"/api/v1/comment/"+pId;

        mvc.perform(post(url)//url http method
                .contentType(MediaType.APPLICATION_JSON_UTF8)//헤더타입
                .content(new ObjectMapper().writeValueAsString(dto)))//request body
                .andExpect(status().isOk());// 재대로 응답왔는지 확인

        List<Comment> all = commentRepository.findAll();
        Product test = productRepository.findById(pId).orElseThrow(() -> new IllegalArgumentException("상품없음"));
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getProduct()).isEqualTo(test);
        assertThat(test.getComments().contains(all.get(0))).isTrue();
    }

    @After
    public void cleanUp(){
        productRepository.deleteAll();
        commentRepository.deleteAll();
    }


}
