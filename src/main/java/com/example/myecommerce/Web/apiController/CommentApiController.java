package com.example.myecommerce.Web.apiController;


import com.example.myecommerce.Service.Comment.CommentService;
import com.example.myecommerce.Web.Dto.Comment.CommentByProductListResDto;
import com.example.myecommerce.Web.Dto.Comment.CommentResDto;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "댓글 controller")
@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @ApiOperation(value = "댓글 조회")
    @GetMapping("/api/v1/comment/{id}")
    public CommentResDto findById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @ApiOperation(value = "댓글 생성")
    @PostMapping("/api/v1/comment/{pId}")
    public Long save(@PathVariable Long pId, @RequestBody CommentReqDto dto) {
        return commentService.save(pId, dto);
    }

    @ApiOperation(value = "댓글 수정")
    @PutMapping("/api/v1/comment/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentReqDto dto) {
        return commentService.update(id, dto);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/api/v1/comment/{id}")
    public Long delete(@PathVariable Long id) {
        commentService.delete(id);
        return id;
    }

    @ApiOperation(value = "상품 댓글 목록")
    @GetMapping("/api/v1/comment/{pid}/product")
    public List<CommentByProductListResDto> findByProductId(@PathVariable("pid")Long id){
        return commentService.findByProductId(id);
    }
}
