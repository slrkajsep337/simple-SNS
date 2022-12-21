package com.example.finalprojectsujin221220.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),
    NOT_FOUND_USER_NAME(HttpStatus.NOT_FOUND, "User name is not found"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Password is not correct");


    private HttpStatus status;
    private String message;

}
