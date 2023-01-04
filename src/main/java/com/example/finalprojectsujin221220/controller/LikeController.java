package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LikeController {

    private final LikeService ls;

    @PostMapping("/posts/{postId}/likes")
    public Response pushLike(@PathVariable Long postId, Authentication authentication) {
        return Response.success(ls.pushLike(postId, authentication));
    }

    @GetMapping("/posts/{postId}/likes")
    public Response countLike(@PathVariable Long postId) {
        return Response.success(ls.countLike(postId));
    }


}
