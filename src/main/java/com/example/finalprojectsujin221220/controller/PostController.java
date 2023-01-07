package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {

    private final PostService ps;

    //포스트 등록
    @PostMapping
    public Response<PostCreateResponse> newPost(@RequestBody PostCreateRequest dto, Authentication authentication) {
        return Response.success(ps.newPost(dto, authentication));
    }

    //포스트 한개 조회
    @GetMapping("/{id}")
    public Response<PostDetailsResponse> showOnePost(@PathVariable Long id) {
        return Response.success(ps.showOnePost(id));
    }

    //포스트 전체 조회
    @GetMapping
    public Response<Page<PostDetailsResponse>> showList(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(ps.showPosts(pageable));
    }

    //포스트 수정
    @PutMapping("/{id}")
    public Response<PostModifyResponse> modifyPost(@PathVariable Long id, @RequestBody PostModifyRequest dto, Authentication authentication) {
        return Response.success(ps.modifyPost(id, dto, authentication));
    }

    //포스트 삭제
    @DeleteMapping("/{id}")
    public Response deletePost(@PathVariable Long id, Authentication authentication) {
        return Response.success(ps.deletePost(id, authentication));
    }

    //내 포스트 보기
    @GetMapping("/my")
    public Response<Page<PostListResponse>> showComments(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return Response.success(ps.showMyPosts(pageable, authentication));
    }



}