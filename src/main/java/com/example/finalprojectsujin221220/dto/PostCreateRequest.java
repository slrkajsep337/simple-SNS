package com.example.finalprojectsujin221220.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostCreateRequest {

    private Long userId;

    private String title;
    private String body;

}
