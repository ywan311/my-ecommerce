package com.example.myecommerce.Web.Dto.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateReqDto {
    private String title;
    private String content;
    private int price;

    @Builder
    public ProductUpdateReqDto(String title, String content, int price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }
}
