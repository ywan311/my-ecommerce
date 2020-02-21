package com.example.myecommerce.Web.Dto.Product;

import com.example.myecommerce.Domain.Product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveReqDto {
    private String title;
    private String content;
    private int price;

    @Builder
    public ProductSaveReqDto(String title, String content, int price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Product toEntity(){
        return Product.builder()
                .title(title)
                .content(content)
                .price(price)
                .build();
    }
}
