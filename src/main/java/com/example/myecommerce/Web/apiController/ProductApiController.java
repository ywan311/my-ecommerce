package com.example.myecommerce.Web.apiController;

import com.example.myecommerce.Service.Product.ProductService;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import com.example.myecommerce.Web.Dto.Product.ProductListResDto;
import com.example.myecommerce.Web.Dto.Product.ProductResDto;
import com.example.myecommerce.Web.Dto.Product.ProductSaveReqDto;
import com.example.myecommerce.Web.Dto.Product.ProductUpdateReqDto;
import io.swagger.annotations.Api;
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
    public Long save(@ApiParam(value = "상품 요청 DTO") @RequestBody ProductSaveReqDto dto) {
        return productService.save(dto);
    }

    @ApiOperation(value = "상품 리스트")
    @GetMapping("/api/v1/product")
    public List<ProductListResDto> findList() {
        return productService.findAll();
    }

    @ApiOperation(value = "상품 조회")
    @GetMapping("/api/v1/product/{id}")
    public ProductResDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @ApiOperation(value = "상품 수정")
    @PutMapping("/api/v1/product/{id}")
    public Long update(@PathVariable Long id, @ApiParam(value = "수정내용 DTO") @RequestBody ProductUpdateReqDto dto) {
        return productService.update(id, dto);
    }

    @ApiOperation(value = "상품삭제")
    @DeleteMapping("/api/v1/product/{id}")
    public Long delete(@PathVariable Long id) {
        productService.delete(id);
        return id;
    }
}
