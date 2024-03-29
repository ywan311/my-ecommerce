package com.example.myecommerce.api.controller;

import com.example.myecommerce.api.config.Security.JwtTokenProvider;
import com.example.myecommerce.api.dto.User.*;
import com.example.myecommerce.api.service.User.CustomUserDetailService;
import com.example.myecommerce.api.service.User.UserService;
import com.example.myecoomerce.myecommercecore.User.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = "UserController")
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final CustomUserDetailService userDetailService;
    private final JwtTokenProvider provider;
    private final UserService userService;

    @ApiOperation(value="로그인")
    @PostMapping(value = "/api/v1/login")
    public String login(@RequestBody LoginReqDto dto){
        User user = (User)userDetailService.loadUserByUsername(dto.getUsername());
        String result = userService.checkLogin(dto.getUsername(),dto.getPassword())?provider.createToken(user.getUsername(),user.getRoleKey()):null;
        if(result==null)return "failed";
        Authentication auth = provider.getAuthentication(result);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return result;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping(value = "api/v1/signup")
    public String register(@RequestBody UserRegisterReqDto dto){
        return userService.register(dto)?"OK":"failed";
    }


    @ApiOperation(value = "내정보")
    @GetMapping(value = "api/v1/my")
    public UserResDto myInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new UserResDto(userService.findByUsername(authentication.getName()));
    }

    @ApiOperation(value = "회원정보 수정")
    @PutMapping(value = "api/v1/my")
    public Long update( @RequestBody UserUpdateReqDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.update(authentication.getName(),dto);
    }

    @ApiOperation(value = "회원삭제")
    @DeleteMapping(value = "api/v1/my/{id}")
    public Long delete(@PathVariable Long id){
        userService.delete(id);
        return id;
    }

    @ApiOperation(value = "비밀번호 변경")
    @PostMapping(value = "/api/v1/my/pass")
    public Long updatePassword(@RequestBody PasswordUpdateReqDto dto){
        return userService.updatePassword(dto);
    }

}
