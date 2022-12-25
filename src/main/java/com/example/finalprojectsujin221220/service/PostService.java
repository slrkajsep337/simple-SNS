package com.example.finalprojectsujin221220.service;


import com.example.finalprojectsujin221220.configuration.JwtFilter;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.PostCreateRequest;
import com.example.finalprojectsujin221220.dto.PostCreateResponse;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import com.example.finalprojectsujin221220.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
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

}
