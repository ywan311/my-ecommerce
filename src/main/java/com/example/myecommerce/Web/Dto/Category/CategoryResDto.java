package com.example.myecommerce.Web.Dto.Category;

import com.example.myecommerce.Domain.Category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CategoryResDto {
    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    @Builder
    public CategoryResDto(Category entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }
}
