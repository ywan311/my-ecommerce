package com.example.myecommerce.Service.Product;

import com.example.myecommerce.Domain.Category.Category;
import com.example.myecommerce.Domain.Category.CategoryRepository;
import com.example.myecommerce.Domain.Comment.CommentRepository;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import com.example.myecommerce.Web.Dto.Product.ProductListResDto;
import com.example.myecommerce.Web.Dto.Product.ProductResDto;
import com.example.myecommerce.Web.Dto.Product.ProductSaveReqDto;
import com.example.myecommerce.Web.Dto.Product.ProductUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(ProductSaveReqDto dto){
        if(dto.getCategoryId()!=null){
            Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(()-> new IllegalArgumentException("카테로리가 없습니다. id:"+dto.getCategoryId()));
            return productRepository.save(dto.toEntity(category)).getId();
        }else return productRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public List<ProductListResDto> findAll(){
        return productRepository.findAll(Sort.by("id").descending())
                .stream()
                .map(ProductListResDto::new)
                .collect(Collectors.toList());
    }

    public ProductResDto findById(Long id){
        Product entity = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("상품이 없습니다. id:"+id));
        return new ProductResDto(entity);
    }

    @Transactional
    public Long update(Long id, ProductUpdateReqDto dto){
        Product entity = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("상품이 없습니다. id:"+id));
        entity.update(dto.getTitle(),dto.getContent(),dto.getPrice());
        if(dto.getCategoryId()!=null&&entity.getCategory().getId()!=dto.getCategoryId())entity.changeCategory(categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new IllegalArgumentException("카테로리가 없습니다. id:"+dto.getCategoryId())));
        return id;
    }

    @Transactional
    public void delete(Long id){
        Product entity = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("상품이 없습니다. id:"+id));
        productRepository.delete(entity);
    }

}
