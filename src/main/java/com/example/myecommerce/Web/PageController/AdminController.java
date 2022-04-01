package com.example.myecommerce.Web.pageController;

import com.example.myecommerce.Service.Product.ProductService;
import com.example.myecommerce.Web.Dto.Product.ProductListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mv) {
        List<ProductListResDto> list = productService.findAll();
        mv.setViewName("board");
        mv.addObject("productList", list);
        mv.addObject("title", "상품 관리");
        return mv;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "board";
    }

    @GetMapping("/index")
    public String indextest(){
        return "index";
    }
}
