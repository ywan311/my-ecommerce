package com.example.myecommerce.Web.ApiController;

import com.example.myecommerce.Domain.User.User;
import com.example.myecommerce.Service.User.CustomUserDetailService;
import com.example.myecommerce.Service.User.UserService;
import com.example.myecommerce.Web.Dto.User.LoginReqDto;
import com.example.myecommerce.Web.Dto.User.UserRegisterReqDto;
import com.example.myecommerce.config.Security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "UserController")
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final CustomUserDetailService userDetailService;
    private final JwtTokenProvider provider;
    private final UserService userService;

    @ApiOperation(value="login")
    @GetMapping(value = "/api/v1/login")
    public String login(@RequestBody LoginReqDto dto){
        User user = (User)userDetailService.loadUserByUsername(dto.getUsername());
        return userService.checkLogin(dto.getUsername(),dto.getPassword())?provider.createToken(user.getUsername(),user.getRoleKey()):"failed";
    }

    @ApiOperation(value = "signup")
    @GetMapping(value = "api/v1/signup")
    public String register(@RequestBody UserRegisterReqDto dto){
        return userService.register(dto.getUsername(),dto.getPassword(),dto.getName())?"OK":"failed";
    }
}
