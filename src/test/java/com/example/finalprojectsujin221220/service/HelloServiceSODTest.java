package com.example.finalprojectsujin221220.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Annotation없이 만듭니다.
class HelloServiceSODTest {

    // Spring을 안쓰고 테스트 하기 때문에 new를 이용해 초기화를 해줍니다.
    // Pojo방식을 최대한 활용합니다.
    HelloService helloService = new HelloService();

    @Test
    @DisplayName("자릿수 합 잘 구하는지")
    void sumOfDigit() {
        assertEquals(21, helloService.sumOfDigit(687));
        assertEquals(22, helloService.sumOfDigit(787));
        assertEquals(0, helloService.sumOfDigit(0));
        assertEquals(5, helloService.sumOfDigit(11111));
    }
}

