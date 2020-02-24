package com.example.myecommerce.Web;

import com.example.myecommerce.Service.Category.CategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "카테고리 controller")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
}
