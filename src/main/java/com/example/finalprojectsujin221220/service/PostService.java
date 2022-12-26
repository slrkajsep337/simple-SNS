package com.example.finalprojectsujin221220.service;


import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.PostCreateRequest;
import com.example.finalprojectsujin221220.dto.PostCreateResponse;
import com.example.finalprojectsujin221220.dto.PostDetailsResponse;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public PostDetailsResponse showOnePost(Long id) {
        Optional<Post> postOpt = pr.findById(id);

        return PostDetailsResponse.builder()
                .id(postOpt.get().getPostId())
                .title(postOpt.get().getTitle())
                .body(postOpt.get().getBody())
                .userName(postOpt.get().getUserName())
                .createdAt(postOpt.get().getCreatedAt())
                .LastModifiedAt(postOpt.get().getLastModifiedAt())
                .build();

    }

}
