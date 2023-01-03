package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.CommentCreateRequest;
import com.example.finalprojectsujin221220.dto.CommentCreateResponse;
import com.example.finalprojectsujin221220.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService cs;

    @PostMapping("/posts/{postId}/comments")
    public Response<CommentCreateResponse> newComment(@PathVariable Long postId, @RequestBody CommentCreateRequest dto, Authentication authentication) {
        return Response.success(cs.newComment(postId, dto, authentication));
    }



}
