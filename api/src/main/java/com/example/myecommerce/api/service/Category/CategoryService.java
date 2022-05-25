package com.example.myecommerce.api.service.Category;

import com.example.myecommerce.api.dto.Category.CategoryReqDto;
import com.example.myecommerce.api.dto.Category.CategoryResDto;
import com.example.myecoomerce.myecommercecore.Domain.Category.Category;
import com.example.myecoomerce.myecommercecore.Domain.Category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long saveCategory(CategoryReqDto dto) {
        return categoryRepository.save(dto.toEntity()).getId();
    }

    public List<CategoryResDto> findAll() {
        return categoryRepository.findAll(Sort.by("id").descending()).stream().map(CategoryResDto::new).collect(Collectors.toList());
    }

    public CategoryResDto findById(Long id) {
        Category entity = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("카테고리가 없습니다. id:" + id));
        return CategoryResDto.builder().entity(entity).build();
    }

    @Transactional
    public Long update(Long id, CategoryReqDto dto) {
        Category entity = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("카테고리가 없습니다. id:" + id));
        entity.update(dto.getTitle());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Category entity = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("카테고리가 없습니다. id:" + id));
        categoryRepository.delete(entity);
    }

}
