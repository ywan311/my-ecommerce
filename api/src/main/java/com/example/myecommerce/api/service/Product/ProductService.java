package com.example.myecommerce.api.service.Product;

import com.example.myecommerce.api.dto.Product.*;
import com.example.myecoomerce.myecommercecore.Category.Category;
import com.example.myecoomerce.myecommercecore.Category.CategoryRepository;
import com.example.myecoomerce.myecommercecore.Product.Product;
import com.example.myecoomerce.myecommercecore.Product.ProductRepository;
import com.example.myecoomerce.myecommercecore.User.User;
import com.example.myecoomerce.myecommercecore.User.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public Long save(ProductSaveReqDto dto, String username){
        User user = userRepository.findByUsername(username);
        if(dto.getCategoryId()!=null){
            Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(()-> new IllegalArgumentException("카테로리가 없습니다. id:"+dto.getCategoryId()));
            return productRepository.save(dto.toEntity(category,user)).getId();
        }else return productRepository.save(dto.toEntity(user)).getId();
    }

    @Transactional
    public List<ProductListResDto> findAllByUserId(String username){
        return productRepository.findAllByUserUsername(username)
                .stream()
                .map(ProductListResDto::new)
                .collect(Collectors.toList());
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

    public List<ProductListByCategoryResDto> findProductByCategoryId(Long id){
        return productRepository.findProductsByCategoryId(id).stream().map(ProductListByCategoryResDto::new).collect(Collectors.toList());
    }

}
