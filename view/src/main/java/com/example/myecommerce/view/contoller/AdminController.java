package com.example.myecommerce.view.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {

//    private final ProductService productService;

//    @RequestMapping("/")
//    public ModelAndView index(ModelAndView mv) {
//        List<ProductListResDto> list = productService.findAll();
//        mv.setViewName("board");
//        mv.addObject("productList", list);
//        mv.addObject("title", "상품 관리");
//        return mv;
//    }

    @RequestMapping("/hello")
    public String hello() {
        return "board";
    }

    @GetMapping("/index")
    public String indextest(){
        return "index";
    }
}
