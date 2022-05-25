package com.example.myecommerce.api.dto.Product;

import com.example.myecoomerce.myecommercecore.Domain.Category.Category;
import com.example.myecoomerce.myecommercecore.Domain.Product.Product;
import com.example.myecoomerce.myecommercecore.Domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ProductSaveReqDto {
    private String title;
    private String content;
    private int price;
    private Long categoryId;

    @Builder
    public ProductSaveReqDto(String title, String content, int price, Long categoryId) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product toEntity(Category category, User user){
        return Product.builder()
                .title(title)
                .content(content)
                .price(price)
                .category(category)
                .user(user)
                .build();
    }
    public Product toEntity(User user){
        return Product.builder()
                .title(title)
                .content(content)
                .price(price)
                .user(user)
                .build();
    }
}
