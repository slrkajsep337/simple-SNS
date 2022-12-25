package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {

    private final PostService ps;

    @PostMapping
    public Response<PostCreateResponse> newPost(@RequestBody PostCreateRequest dto, Authentication authentication) {
        return Response.success(ps.newPost(dto, authentication));
    }

    @GetMapping("/{id}")
    public Response<PostDetailsResponse> showOnePost(@PathVariable Long id) {
        return Response.success(ps.showOnePost(id));
    }


}
