package com.example.myecommerce.Web.Dto.Product;

import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Web.Dto.Category.CategoryResDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductListByCategoryResDto {
    private Long id;
    private String title;
    private int price;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;


    public ProductListByCategoryResDto(Product entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
