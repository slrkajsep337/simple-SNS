package com.example.finalprojectsujin221220.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDeleteResponse {

    private String message;
    private Long postId;

    public static PostDeleteResponse toResponse(Long postId) {
        return PostDeleteResponse.builder()
                .message("포스트 삭제 완료")
                .postId(postId)
                .build();
    }
}
