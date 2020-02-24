package com.example.myecommerce.Web.Dto.Comment;

import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Product.Product;
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

    public Comment toEntity(Product product){
        return Comment.builder()
                .product(product)
                .title(title)
                .content(content)
                .build();
    }
}
