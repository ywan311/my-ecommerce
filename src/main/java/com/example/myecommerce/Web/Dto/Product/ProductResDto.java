package com.example.myecommerce.Web.Dto.Product;

import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Web.Dto.Comment.CommentResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ProductResDto {
    private String title;
    private String content;
    private int price;
    private Set<CommentResDto> comments;
    private LocalDateTime modifiedDate;

    public ProductResDto(Product entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.price = entity.getPrice();
        this.comments = entity.getComments().stream().map(CommentResDto::new).collect(Collectors.toSet());
        this.modifiedDate = entity.getModifiedDate();
    }
}
