package com.example.finalprojectsujin221220.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public static int sumOfDigit(int num) {
        int answer = 0;
        while (num != 0) {
            answer += num%10;
            num /= 10;
        }

        return answer;
    }
}
