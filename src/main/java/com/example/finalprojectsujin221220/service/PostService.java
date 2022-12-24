package com.example.finalprojectsujin221220.service;


import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.PostCreateRequest;
import com.example.finalprojectsujin221220.dto.PostCreateResponse;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository ur;
    private final PostRepository pr;

    public PostCreateResponse newPost(PostCreateRequest dto) {

        Optional<User> userOpt = ur.findById(dto.getUserId());
        Post post = Post.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .userName(dto.getUserName())
                .createdAt(dto.getCreatedAt())
                .lastModifiedAt(dto.getLastModifiedAt())
                .user(userOpt.get())
                .build();

        Post savedPost = pr.save(post);
        return PostCreateResponse.builder()
                .title(savedPost.getTitle())
                .body(savedPost.getBody())
                .userName(savedPost.getUserName())
                .createdAt(savedPost.getCreatedAt())
                .lastModifiedAt(savedPost.getLastModifiedAt())
                .message("게시물 등록이 성공했습니다")
                .build();
    }

}
