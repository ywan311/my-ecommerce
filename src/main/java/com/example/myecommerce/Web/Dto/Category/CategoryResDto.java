package com.example.myecommerce.Web.Dto.Category;

import com.example.myecommerce.Domain.Category.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryResDto {
    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    public CategoryResDto(Category entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }
}
