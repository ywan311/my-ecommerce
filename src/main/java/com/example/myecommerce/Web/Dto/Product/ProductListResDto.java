package com.example.myecommerce.Web.Dto.Product;

import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class ProductListResDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private LocalDateTime modifiedDate;

    public ProductListResDto(Product entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.price = entity.getPrice();
        this.modifiedDate = entity.getModifiedDate();
    }
}
