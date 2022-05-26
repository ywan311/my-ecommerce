package com.example.myecommerce.api.service.Comment;

import com.example.myecommerce.api.dto.Comment.CommentByProductListResDto;
import com.example.myecommerce.api.dto.Comment.CommentReqDto;
import com.example.myecommerce.api.dto.Comment.CommentResDto;
import com.example.myecoomerce.myecommercecore.Comment.Comment;
import com.example.myecoomerce.myecommercecore.Comment.CommentRepository;
import com.example.myecoomerce.myecommercecore.Product.Product;
import com.example.myecoomerce.myecommercecore.Product.ProductRepository;
import com.example.myecoomerce.myecommercecore.User.User;
import com.example.myecoomerce.myecommercecore.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CommentResDto findById(Long id){
        Comment entity = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글이 없습니다. id:"+id));
        return new CommentResDto(entity);
    }

    @Transactional
    public Long save(Long prodId, CommentReqDto dto, String username){
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(prodId).orElseThrow(()-> new IllegalArgumentException("상품이 없습니다. id:"+prodId));
        Comment newComment = commentRepository.save(dto.toEntity(product,user));
        product.addComment(newComment);
        return newComment.getId();
    }

    public Long update(Long id, CommentReqDto dto){
        Comment entity = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글이 없습니다. id:"+id));
        entity.update(dto.getTitle(),dto.getContent());
        return id;
    }
    public void delete(Long id){
        Comment entity = commentRepository .findById(id).orElseThrow(()->new IllegalArgumentException("댓글이 없습니다. id:"+id));
        commentRepository.delete(entity);
    }

    public List<CommentByProductListResDto> findByProductId(Long id){
        List<Comment> result = commentRepository.findCommentsByProductId(id);
        return result.stream().map(o ->CommentByProductListResDto.builder().id(o.getId()).title(o.getTitle()).modifiedDate(o.getModifiedDate()).build()).collect(Collectors.toList());
    }
}
