import com.example.myecoomerce.myecommercecore.Domain.Category.Category;
import com.example.myecoomerce.myecommercecore.Domain.Category.CategoryRepository;
import com.example.myecoomerce.myecommercecore.Domain.Product.Product;
import com.example.myecoomerce.myecommercecore.Domain.Product.ProductRepository;
import com.example.myecoomerce.myecommercecore.Domain.User.Role;
import com.example.myecoomerce.myecommercecore.Domain.User.User;
import com.example.myecoomerce.myecommercecore.Domain.User.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ImageFileApiControllerTest {


    @Autowired
    private WebApplicationContext context;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockMvc mvc;

    private Category test;
    private User user;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        user = User.builder().username("testusername").password(passwordEncoder.encode("test")).role(Role.USER).name("nanananame").build();
        userRepository.save(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getAuthorities()));
        test = categoryRepository.save(Category.builder().title("카테고리 테스트").build());
    }

    @Test
    public void 파일업로드테스트() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        Product savedProduct = productRepository.save(Product.builder()
                .title("상품 수정전")
                .content("상품내용 수정전")
                .price(123)
                .category(test)
                .user(user)
                .build());
        System.out.println(file);
        System.out.println(savedProduct);

        mvc.perform(multipart("/api/v1/file/" + savedProduct.getId() + "/product").file(file))
                .andExpect(status().isOk());
    }
}