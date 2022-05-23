package com.example.myecommerce.Web.PageController;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/web")
@Controller
public class IndexController {
    @GetMapping("")
    public String index(){
        System.out.println("index controller,index");
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")?this.login():"index";
    }
    @GetMapping("login")
    public String login(){
        System.out.println("index controlle,login");
        return "login";
    }

    @PostMapping("login")
    public String loginPost(){
        return"";
    }

}
