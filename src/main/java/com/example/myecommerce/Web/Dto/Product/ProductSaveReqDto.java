package com.example.myecommerce.Web.Dto.Product;

import com.example.myecommerce.Domain.Category.Category;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.User.User;
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
