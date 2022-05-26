import com.example.myecommerce.api.config.Security.JwtTokenProvider;
import com.example.myecommerce.api.dto.User.LoginReqDto;
import com.example.myecommerce.api.dto.User.UserRegisterReqDto;
import com.example.myecommerce.api.dto.User.UserResDto;
import com.example.myecoomerce.myecommercecore.User.Role;
import com.example.myecoomerce.myecommercecore.User.User;
import com.example.myecoomerce.myecommercecore.User.UserRepository;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void clear(){
        userRepository.deleteAll();
    }

    @Test
    public void 회원등록테스트() throws Exception{
        String username = "회원 테스트";
        String password = "testtest";
        String name = "회원 이름 테스트";
        String email = "email@xxx.com";
        String address = "address";

        UserRegisterReqDto dto = UserRegisterReqDto.builder()
                .username(username)
                .password(password)
                .name(name)
                .address(address)
                .email(email)
                .build();

        String url = "http://localhost:"+port+"/api/v1/signup";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        List<User> list = userRepository.findAll();
        User test = list.get(0);

        assertThat(test.getUsername()).isEqualTo(username);
        assertThat(test.getName()).isEqualTo(name);
        assertThat(test.getAddress()).isEqualTo(address);
        assertThat(test.getEmail()).isEqualTo(email);
        assertThat(test.getRole().getKey()).isEqualTo(Role.USER.getKey());
        assertThat(passwordEncoder.matches(password,test.getPassword())).isTrue();
    }

    @Test
    public void 로그인() throws Exception{
        String username = "로그인 테스트";
        String password = "test";
        String name = "로그인 이름";

        User user = userRepository.save(
                User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .role(Role.USER)
                .build());

        LoginReqDto dto = LoginReqDto.builder()
                .username(username)
                .password(password)
                .build();

        String url = "http://localhost:"+port+"/api/v1/login";

        MvcResult result =mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();
        String token = result.getResponse().getContentAsString();
        System.out.println(token);

        User test = userRepository.findByUsername(username);
        assertThat(provider.getUserPk(token)).isEqualTo(username);
        assertThat(provider.getAuthentication(token).getPrincipal()).isEqualTo(user);
    }

    @Test
    public void 내정보()throws Exception{
        String username = "로그인 테스트";
        String password = "test";
        String name = "로그인 이름";

        User user = userRepository.save(
                User.builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .name(name)
                        .role(Role.USER)
                        .build());

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getAuthorities()));

        String url = "http://localhost:"+port+"/api/v1/my";

        MvcResult result =mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isOk())
                .andReturn();

        String s = result.getResponse().getContentAsString();
        UserResDto test = new ObjectMapper().readValue(s,UserResDto.class);

        assertThat(test.getUsername()).isEqualTo(username);
        assertThat(test.getName()).isEqualTo(name);
    }
}
