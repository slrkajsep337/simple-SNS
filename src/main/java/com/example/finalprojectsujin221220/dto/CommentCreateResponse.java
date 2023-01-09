package com.example.finalprojectsujin221220.dto;


import com.example.finalprojectsujin221220.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentCreateResponse {

    private Long id;
    private String comment;
    private String userName;
    private Long postId;
    private LocalDateTime createdAt;

    public static CommentCreateResponse toResponse(Comment comment) {
        return CommentCreateResponse.builder()
                .id(comment.getCommentId())
                .comment(comment.getComment())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getPostId())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}
