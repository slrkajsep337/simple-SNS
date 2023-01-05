package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService cs;

    //코멘트 등록
    @PostMapping("/posts/{postId}/comments")
    public Response<CommentCreateResponse> newComment(@PathVariable Long postId, @RequestBody CommentCreateRequest dto, Authentication authentication) {
        return Response.success(cs.newComment(postId, dto, authentication));
    }

    //코멘트 수정
    @PutMapping("/posts/{postId}/comments/{id}")
    public Response<CommentModifyResponse> modifyComment(@PathVariable Long postId, @PathVariable Long id, @RequestBody CommentModifyRequest dto, Authentication authentication) {
        return Response.success(cs.modifyComment(postId, id, dto, authentication));
    }

    //코멘트 삭제
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public Response<CommentDeleteResponse> deleteComment(@PathVariable Long postId, @PathVariable Long id, Authentication authentication) {
        return Response.success(cs.deleteComment(postId, id, authentication));
    }

    //코멘트 전체 조회
    @GetMapping("/posts/{postId}/comments")
    public Response<Page<CommentListResponse>> showComments(@PathVariable Long postId, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(cs.showComments(postId, pageable));
    }

}
