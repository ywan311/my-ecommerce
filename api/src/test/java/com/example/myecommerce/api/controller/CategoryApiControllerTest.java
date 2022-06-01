package com.example.myecommerce.api.controller;

import com.example.myecommerce.api.dto.Category.CategoryReqDto;
import com.example.myecoomerce.myecommercecore.Category.Category;
import com.example.myecoomerce.myecommercecore.Category.CategoryRepository;
import com.example.myecoomerce.myecommercecore.Product.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CategoryApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @AfterEach
    public void tearDown() throws Exception {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void category등록테스트() throws Exception {
        //given
        String title = "상품명 테스트";

        CategoryReqDto dto = CategoryReqDto.builder()
                .title(title)
                .build();

        String url = "http://localhost:" + port + "/api/v1/category";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        //then
        List<Category> list = categoryRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
    }

    @Test
    public void 카테고리수정테스트() throws Exception {
        //given

        String expectedTitle = "카테고리수정";
        Category savedCate = categoryRepository.save(Category.builder().title("수정전").build());

        Long updateId = savedCate.getId();
        CategoryReqDto dto = CategoryReqDto.builder().title(expectedTitle).build();

        String url = "http://localhost:" + port + "/api/v1/category/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        //then
        List<Category> list = categoryRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(list.get(0).getId()).isEqualTo(updateId);
    }


}
