package com.example.myecommerce.Service.Comment;

import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Comment.CommentRepository;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Web.Dto.Comment.CommentResDto;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public CommentResDto findById(Long id){
        Comment entity = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글이 없습니다. id:"+id));
        return new CommentResDto(entity);
    }

    @Transactional
    public Long save(Long prodId, CommentReqDto dto){
        Product product = productRepository.findById(prodId).orElseThrow(()-> new IllegalArgumentException("상품이 없습니다. id:"+prodId));
        return commentRepository.save(dto.toEntity(product,dto.getTitle(),dto.getContent())).getId();
    }

    public Long update(Long id, CommentReqDto dto){
        Comment entity = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글이 없습니다. id:"+id));
        entity.update(dto.getTitle(),dto.getContent());
        return id;
    }
    public void delete(Long id){
        Comment entity = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글이 없습니다. id:"+id));
        commentRepository.delete(entity);
    }
}
