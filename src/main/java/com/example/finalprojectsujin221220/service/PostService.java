package com.example.finalprojectsujin221220.service;


import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {

    private final UserRepository ur;
    private final PostRepository pr;


    public PostCreateResponse newPost(PostCreateRequest dto, Authentication authentication) {

        Optional<User> userOpt = ur.findByUserName(authentication.getName());
        Post post = Post.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .userName(authentication.getName())
                .createdAt(LocalDateTime.now())
                .lastModifiedAt(LocalDateTime.now())
                .user(userOpt.get())
                .build();

        Post savedPost = pr.save(post);
        return PostCreateResponse.builder()
                .message("포스트 등록 완료")
                .postId(savedPost.getPostId())
                .build();
    }

    public PostDetailsResponse showOnePost(Long id) {
        Optional<Post> postOpt = pr.findById(id);

        return PostDetailsResponse.builder()
                .id(postOpt.get().getPostId())
                .title(postOpt.get().getTitle())
                .body(postOpt.get().getBody())
                .userName(postOpt.get().getUserName())
                .createdAt(postOpt.get().getCreatedAt())
                .lastModifiedAt(postOpt.get().getLastModifiedAt())
                .build();
    }

    public List<PostDetailsResponse> showPosts(Pageable pageable) {
        Page<Post> posts = pr.findAll(pageable);
        List<PostDetailsResponse> postResponses = posts.stream()
                .map(post -> Post.of(post)).collect(Collectors.toList());
        return postResponses;
    }

    public PostModifyResponse modifyPost(Long postId, PostModifyRequest dto, Authentication authentication) {
        User user = ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));

        Post post = pr.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));

        if( !post.getUser().equals(user)) throw new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());

        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        post.setLastModifiedAt(LocalDateTime.now());

        Post savedPost = pr.save(post);

        return PostModifyResponse.builder()
                .message("포스트 수정 완료")
                .postId(savedPost.getPostId())
                .build();
    }


    public PostDeleteResponse deletePost(Long postId, Authentication authentication) {
        User user = ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
        Post post = pr.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));

        if(!post.getUser().equals(user)) throw new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());

        pr.delete(post);

        return PostDeleteResponse.builder()
                .message("포스트 삭제 완료")
                .postId(post.getPostId())
                .build();

    }
}
