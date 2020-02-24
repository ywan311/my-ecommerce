package com.example.myecommerce.Web.Dto.Category;

import com.example.myecommerce.Domain.Category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryReqDto {
    private String title;

    @Builder
    public CategoryReqDto(String title) {
        this.title = title;
    }

    public Category toEntity(String title){
        return Category.builder()
                .title(title)
                .build();
    }
}
