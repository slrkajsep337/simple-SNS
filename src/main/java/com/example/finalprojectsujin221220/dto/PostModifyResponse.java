package com.example.finalprojectsujin221220.dto;


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

}
