package com.example.myecommerce.Web.ApiController;

import com.example.myecommerce.Domain.Order.Order;
import com.example.myecommerce.Domain.Order.OrderRepository;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Domain.User.Role;
import com.example.myecommerce.Domain.User.User;
import com.example.myecommerce.Domain.User.UserRepository;
import com.example.myecommerce.Web.Dto.Order.OrderListResDto;
import com.example.myecommerce.Web.Dto.Product.ProductUpdateReqDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class OrderApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private Long prodId;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        User user = User.builder().username("testusername").password(passwordEncoder.encode("test")).role(Role.USER).name("nanananame").build();
        userRepository.save(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getAuthorities()));

        String title = "상품명 테스트";
        String content = "상품내용 테스트";
        int price = 1515;

        prodId = productRepository.save(Product.builder()
                .title(title)
                .content(content)
                .price(price)
                .user(user)
                .build()).getId();

    }


    @After
    public void tearDown() throws Exception {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 주문정보() throws Exception {

        //given


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        Product product = productRepository.findById(prodId).orElseThrow(() -> new IllegalArgumentException());

        orderRepository.save(Order.builder()
                .user(user)
                .product(product)
                .build());

        String url = "http://localhost:" + port + "/api/v1/order";

        //when
        MvcResult result = mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isOk())
                .andReturn();

        List<OrderListResDto> test = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ArrayList.class);

        //then
        assertThat(test.get(0).getProductContent()).isEqualTo(product.getContent());
        assertThat(test.get(0).getProductTitle()).isEqualTo(product.getTitle());
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
    }

}
