package com.example.finalprojectsujin221220.dto;

import com.example.finalprojectsujin221220.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostCreateResponse {

    private String message;
    private Long postId;

    public static PostCreateResponse toResponse(Post post) {
        return PostCreateResponse.builder()
                .message("포스트 등록 완료")
                .postId(post.getPostId())
                .build();
    }

}
