package com.example.myecommerce.api.controller;

import com.example.myecommerce.api.dto.Product.ProductSaveReqDto;
import com.example.myecommerce.api.dto.Product.ProductUpdateReqDto;
import com.example.myecoomerce.myecommercecore.Category.Category;
import com.example.myecoomerce.myecommercecore.Category.CategoryRepository;
import com.example.myecoomerce.myecommercecore.Product.Product;
import com.example.myecoomerce.myecommercecore.Product.ProductRepository;
import com.example.myecoomerce.myecommercecore.User.Role;
import com.example.myecoomerce.myecommercecore.User.User;
import com.example.myecoomerce.myecommercecore.User.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProductApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private User user;

    private Category test;
    @BeforeEach
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        user = User.builder().username("testusername").password(passwordEncoder.encode("test")).role(Role.USER).name("nanananame").build();
        userRepository.save(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getAuthorities()));

        test = categoryRepository.save(Category.builder().title("카테고리 테스트").build());
    }


    @AfterEach
    public void tearDown() throws Exception {
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void Product_등록테스트() throws Exception {

        //given
        String title = "상품명 테스트";
        String content = "상품내용 테스트";
        int price = 1515;

        ProductSaveReqDto dto = ProductSaveReqDto.builder()
                .title(title)
                .content(content)
                .price(price)
                .categoryId(test.getId())
                .build();

        String url = "http://localhost:" + port + "/api/v1/product";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        //then
        List<Product> list = productRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);
        assertThat(list.get(0).getPrice()).isEqualTo(price);
        assertThat(list.get(0).getComments().isEmpty()).isTrue();
        assertThat(list.get(0).getCategory().getId()).isEqualTo(test.getId());
    }

    @Test
    public void Product_수정테스트() throws Exception {
        //given

        String expectedTitle = "상품명 수정 테스트";
        String expectedContent = "상품내용 수정 테스트";
        int expectedPrice = 123456;

        Product savedProduct = productRepository.save(Product.builder()
                .title("상품 수정전")
                .content("상품내용 수정전")
                .price(123)
                .user(user)
                .build());

        Long updateId = savedProduct.getId();
        ProductUpdateReqDto dto = ProductUpdateReqDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .price(expectedPrice)
                .build();

        String url = "http://localhost:" + port + "/api/v1/product/" + updateId;

        //when
         mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        //then
        List<Product> list = productRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(list.get(0).getContent()).isEqualTo(expectedContent);
        assertThat(list.get(0).getPrice()).isEqualTo(expectedPrice);
        assertThat(list.get(0).getComments().isEmpty()).isTrue();
        assertThat(list.get(0).getCategory()).isNull();
        assertThat(list.get(0).getUser()).isEqualTo(user);
    }

}
