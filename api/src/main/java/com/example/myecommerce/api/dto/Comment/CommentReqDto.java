package com.example.myecommerce.api.dto.Comment;

import com.example.myecoomerce.myecommercecore.Comment.Comment;
import com.example.myecoomerce.myecommercecore.Product.Product;
import com.example.myecoomerce.myecommercecore.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentReqDto {
    private String title;
    private String content;

    @Builder
    public CommentReqDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Comment toEntity(Product product, User user){
        return Comment.builder()
                .product(product)
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
