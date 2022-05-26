package com.example.myecommerce.api.dto.Product;

import com.example.myecommerce.api.dto.Category.CategoryResDto;
import com.example.myecoomerce.myecommercecore.Product.Product;
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
