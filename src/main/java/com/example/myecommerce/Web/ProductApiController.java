package com.example.myecommerce.Web;

import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Service.Product.ProductService;
import com.example.myecommerce.Web.Dto.Product.ProductSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;

    @PostMapping("/api/v1/product")
    public Long save(@RequestBody ProductSaveReqDto dto){
        return productService.save(dto);
    }
}
