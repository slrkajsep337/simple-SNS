package com.example.finalprojectsujin221220.domain;

import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private ErrorCode errorCode;
    private String message;


}
