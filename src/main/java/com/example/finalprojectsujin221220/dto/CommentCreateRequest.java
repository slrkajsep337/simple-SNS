package com.example.finalprojectsujin221220.dto;

import com.example.finalprojectsujin221220.domain.entity.Comment;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import lombok.Getter;

@Getter
public class CommentCreateRequest {

    private String comment;

    public Comment toEntity(User user, Post post) {
        return Comment.builder()
                .comment(this.getComment())
                .user(user)
                .post(post)
                .build();
    }
}
