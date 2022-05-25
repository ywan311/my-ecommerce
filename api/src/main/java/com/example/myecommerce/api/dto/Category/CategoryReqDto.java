package com.example.myecommerce.api.dto.Category;

import com.example.myecoomerce.myecommercecore.Domain.Category.Category;
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

    public Category toEntity() {
        return Category.builder()
                .title(this.title)
                .build();
    }
}
