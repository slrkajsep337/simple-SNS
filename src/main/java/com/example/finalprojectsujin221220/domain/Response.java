package com.example.finalprojectsujin221220.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;

    public static <T> Response<T> error(T result) {
        return new Response("ERROR", result);
    }

    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
}

