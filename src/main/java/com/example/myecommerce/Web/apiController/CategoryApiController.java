package com.example.myecommerce.Web.apiController;

import com.example.myecommerce.Service.Category.CategoryService;
import com.example.myecommerce.Web.Dto.Category.CategoryReqDto;
import com.example.myecommerce.Web.Dto.Category.CategoryResDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "카테고리 controller")
@RestController
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryService categoryService;

    @GetMapping("/api/v1/category")
    public List<CategoryResDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/api/v1/category/{id}")
    public CategoryResDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/api/v1/category")
    public Long saveCategory(@RequestBody CategoryReqDto dto) {
        return categoryService.saveCategory(dto);
    }

    @PutMapping("/api/v1/category/{id}")
    public Long update(@PathVariable Long id, @RequestBody CategoryReqDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/api/v1/category/{id}")
    public Long delete(@PathVariable Long id) {
        categoryService.delete(id);
        return id;
    }
}
