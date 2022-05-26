package com.example.myecommerce.api.dto.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentByProductListResDto {
    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    @Builder
    public CommentByProductListResDto(Long id, String title, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.modifiedDate = modifiedDate;
    }
}
