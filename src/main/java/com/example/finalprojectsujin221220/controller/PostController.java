package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"Post Api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {

    private final PostService ps;

    //포스트 등록
    @Operation(summary = "post 등록", description = "인증된 user만 post를 등록할 수 있습니다.")
    @PostMapping
    public Response<PostCreateResponse> newPost(@RequestBody PostCreateRequest dto, Authentication authentication) {
        return Response.success(ps.newPost(dto, authentication));
    }

    //포스트 한개 조회
    @Operation(summary = "post 한개 조회", description = "인증 없이도 특정 post를 조회할 수 있습니다.")
    @GetMapping("/{id}")
    public Response<PostDetailsResponse> showOnePost(@PathVariable Long id) {
        return Response.success(ps.showOnePost(id));
    }

    //포스트 전체 조회
    @Operation(summary = "post 전체 조회", description = "인증 없이도 전체 post를 조회할 수 있습니다.")
    @GetMapping
    public Response<Page<PostListResponse>> showList(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(ps.showPosts(pageable));
    }

    //포스트 수정
    @Operation(summary = "post 수정", description = "인증된(post를 등록한) user만 post를 수정할 수 있습니다.")
    @PutMapping("/{id}")
    public Response<PostModifyResponse> modifyPost(@PathVariable Long id, @RequestBody PostModifyRequest dto, Authentication authentication) {
        return Response.success(ps.modifyPost(id, dto, authentication));
    }

    //포스트 삭제
    @Operation(summary = "post 삭제", description = "인증된(post를 등록한) user만 post를 삭제할 수 있습니다.")
    @DeleteMapping("/{id}")
    public Response<PostDeleteResponse> deletePost(@PathVariable Long id, Authentication authentication) {
        return Response.success(ps.deletePost(id, authentication));
    }

    //내 포스트 보기
    @Operation(summary = "my post 조회하기", description = "인증된 user만 자신의 post를 조회할 수 있습니다.")
    @GetMapping("/my")
    public Response<Page<MyPostListResponse>> showComments(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return Response.success(ps.showMyPosts(pageable, authentication));
    }



}
