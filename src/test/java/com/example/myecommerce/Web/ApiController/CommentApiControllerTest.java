package com.example.myecommerce.Web.ApiController;

import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Comment.CommentRepository;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Domain.User.Role;
import com.example.myecommerce.Domain.User.User;
import com.example.myecommerce.Domain.User.UserRepository;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Product test;



    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        User user = User.builder().username("testusername").password(passwordEncoder.encode("test")).role(Role.USER).name("nanananame").build();
        userRepository.save(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getAuthorities()));

        String pTitle = "상품명 테스트";
        String pContent = "상품내용 테스트";
        int price = 1515;

        test = productRepository.save(Product.builder()
                .title(pTitle)
                .content(pContent)
                .price(price)
                .user(user)
                .build());
    }



    @Test
    public void 댓글등록() throws Exception {
        String title = "댓글등록 테스트";
        String content = "댓글 내용";

        CommentReqDto dto = CommentReqDto.builder()
                .title(title)
                .content(content)
                .build();


        String url = "http://localhost:" + port + "/api/v1/comment/" + test.getId();

        mvc.perform(post(url)//url http method
                .contentType(MediaType.APPLICATION_JSON_UTF8)//헤더타입
                .content(new ObjectMapper().writeValueAsString(dto)))//request body
                .andExpect(status().isOk());// 재대로 응답왔는지 확인

        List<Comment> all = commentRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getProduct()).isEqualTo(test);
        assertThat(test.getComments().contains(all.get(0))).isTrue();
    }

    @Test
    public void 댓글수정() throws Exception {
        String expectedTitle = "댓글등록 테스트";
        String expectedContent = "댓글 내용";

        CommentReqDto dto = CommentReqDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        Comment comment = commentRepository.save(Comment.builder()
                .title("수정전")
                .content("수정전")
                .product(test)
                .build());


        String url = "http://localhost:" + port + "/api/v1/comment/" + comment.getId();

        mvc.perform(put(url)//url http method
                .contentType(MediaType.APPLICATION_JSON_UTF8)//헤더타입
                .content(new ObjectMapper().writeValueAsString(dto)))//request body
                .andExpect(status().isOk());// 재대로 응답왔는지 확인

        List<Comment> list = commentRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(list.get(0).getContent()).isEqualTo(expectedContent);
        assertThat(list.get(0).getProduct()).isEqualTo(test);
    }

    @After
    public void cleanUp() {
        commentRepository.deleteAll();
        productRepository.deleteAll();
    }


}
