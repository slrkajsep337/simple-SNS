package com.example.finalprojectsujin221220.dto;


import com.example.finalprojectsujin221220.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDetailsResponse {

    private Long id;
    private String title;
    private String body;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public static PostDetailsResponse toResponse(Post post) {
        return PostDetailsResponse.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUserName())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .build();
    }

}
