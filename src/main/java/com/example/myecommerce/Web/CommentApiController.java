package com.example.myecommerce.Web;


import com.example.myecommerce.Service.Comment.CommentService;
import com.example.myecommerce.Web.Dto.Comment.CommentResDto;
import com.example.myecommerce.Web.Dto.Comment.CommentReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @GetMapping("/api/v1/comment/{id}")
    public CommentResDto findById(@PathVariable Long id){
        return commentService.findById(id);
    }

    @PostMapping("/api/v1/comment/{pId}")
    public Long save(@PathVariable Long pId,@RequestBody CommentReqDto dto){
        return commentService.save(pId,dto);
    }

    @PutMapping("/api/v1/comment/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentReqDto dto){
        return commentService.update(id, dto);
    }

    @DeleteMapping("/api/v1/comment/{id}")
    public Long delete(@PathVariable Long id){
        commentService.delete(id);
        return id;
    }
}
