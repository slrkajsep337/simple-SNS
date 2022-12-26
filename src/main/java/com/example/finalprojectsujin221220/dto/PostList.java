package com.example.finalprojectsujin221220.dto;

import com.example.finalprojectsujin221220.domain.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostList {

    private final Long id;
    private final String title;
    private final String body;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

}

