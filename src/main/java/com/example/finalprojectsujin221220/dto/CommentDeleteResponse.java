package com.example.finalprojectsujin221220.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentDeleteResponse {

    private String message;
    private Long id;

    public static CommentDeleteResponse toResponse(Long id) {
        return CommentDeleteResponse.builder()
                .message("댓글 삭제 완료")
                .id(id)
                .build();
    }

}
