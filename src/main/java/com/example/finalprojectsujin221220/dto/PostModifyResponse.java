package com.example.finalprojectsujin221220.dto;


import com.example.finalprojectsujin221220.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostModifyResponse {

    private String message;
    private Long postId;

    public static PostModifyResponse toResponse(Post post) {
        return PostModifyResponse.builder()
                .message("포스트 수정 완료")
                .postId(post.getPostId())
                .build();
    }

}
