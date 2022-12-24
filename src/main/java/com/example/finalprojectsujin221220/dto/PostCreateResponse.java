package com.example.finalprojectsujin221220.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostCreateResponse {

    private String title;
    private String body;
    private String userName;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    private String message;

}
