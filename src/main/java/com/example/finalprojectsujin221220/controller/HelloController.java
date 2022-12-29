package com.example.finalprojectsujin221220.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "이수진";
    }

    @GetMapping("/hello/{num}")
    public int sumOfDigit(@PathVariable int num) {
        int answer = 0;
        while (num != 0) {
            answer += num%10;
            num /= 10;
        }

        return answer;
    }


}
