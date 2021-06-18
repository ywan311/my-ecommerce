package com.example.myecommerce.Web.Dto.Comment;

import com.example.myecommerce.Domain.Comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime modifiedDate;

    public CommentResDto(Comment entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }
}
