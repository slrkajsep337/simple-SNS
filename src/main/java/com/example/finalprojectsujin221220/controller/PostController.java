package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping
    public Response<List<PostDetailsResponse>> showList(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(ps.showPosts(pageable));
    }

}
