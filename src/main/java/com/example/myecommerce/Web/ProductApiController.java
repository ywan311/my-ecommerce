package com.example.myecommerce.Web;

import com.example.myecommerce.Service.Product.ProductService;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import com.example.myecommerce.Web.Dto.Product.ProductListResDto;
import com.example.myecommerce.Web.Dto.Product.ProductResDto;
import com.example.myecommerce.Web.Dto.Product.ProductSaveReqDto;
import com.example.myecommerce.Web.Dto.Product.ProductUpdateReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ProductController")
@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;

    @ApiOperation(value = "상품 생성")
    @PostMapping("/api/v1/product")
    public Long save(@ApiParam(value ="상품 요청 DTO") @RequestBody ProductSaveReqDto dto) {
        return productService.save(dto);
    }

    @GetMapping("/api/v1/product")
    public List<ProductListResDto> findList() {
        return productService.findAll();
    }

    @GetMapping("/api/v1/product/{id}")
    public ProductResDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/api/v1/product/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductUpdateReqDto dto) {
        return productService.update(id, dto);
    }

    @PostMapping("/api/v1/product/{id}")
    public Long saveComment(@PathVariable Long id, @RequestBody CommentReqDto dto){
        return productService.addComment(id, dto);
    }


    @DeleteMapping("/api/v1/product/{id}")
    public Long delete(@PathVariable Long id){
        productService.delete(id);
        return id;
    }
}
