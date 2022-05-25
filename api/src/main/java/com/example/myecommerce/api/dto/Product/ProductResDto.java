package com.example.myecommerce.api.dto.Product;

import com.example.myecommerce.api.dto.Comment.CommentResDto;
import com.example.myecoomerce.myecommercecore.Domain.Product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductResDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private Set<CommentResDto> comments;
    private LocalDateTime modifiedDate;

    public ProductResDto(Product entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.price = entity.getPrice();
        this.comments = entity.getComments().stream().map(CommentResDto::new).collect(Collectors.toSet());
        this.modifiedDate = entity.getModifiedDate();
    }
}
