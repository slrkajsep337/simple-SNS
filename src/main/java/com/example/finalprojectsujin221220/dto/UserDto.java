package com.example.finalprojectsujin221220.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto { //꼭 필요한지? ..

    private Long id;
    private String userName;
    private String password;

}
