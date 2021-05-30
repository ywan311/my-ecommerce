package com.example.myecommerce.Web.Dto.Product;

import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Web.Dto.Category.CategoryResDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductListResDto {
    private Long id;
    private String title;
    private CategoryResDto category;
    private String content;
    private int price;
    private LocalDateTime modifiedDate;

    public ProductListResDto(Product entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.category = new CategoryResDto(entity.getCategory());
        this.price = entity.getPrice();
        this.modifiedDate = entity.getModifiedDate();
    }
}
