package com.example.finalprojectsujin221220.dto;

import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostCreateRequest {

    private String title;
    private String body;

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.getTitle())
                .body(this.getBody())
                .userName(user.getUserName())
                .user(user)
                .build();

    }

}
