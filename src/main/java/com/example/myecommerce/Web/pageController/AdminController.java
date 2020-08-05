package com.example.myecommerce.Web.pageController;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/")
    public String index(){
        Map<String,String> map = System.getenv();
        for(String key : map.keySet()){
            System.out.println(key+"::"+map.get(key));
        }
        return "board";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "board";
    }
}
