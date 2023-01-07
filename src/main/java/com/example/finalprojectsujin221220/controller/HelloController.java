package com.example.finalprojectsujin221220.controller;

import com.example.finalprojectsujin221220.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HelloController {

    private final HelloService hs;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "이수진";
    }

    @GetMapping("/hello/{num}")
    public int showSum(@PathVariable int num) {
        return hs.sumOfDigit(num);
    }


}
