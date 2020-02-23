package com.example.myecommerce.Web.Dto.Comment;

import com.example.myecommerce.Domain.Comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentResDto {
    private String title;
    private String content;

    public CommentResDto(Comment entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
