package com.practice.springbootpractice.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 클래스 단위
// @Controller: 웹 요청을 받는 컨트롤러
// @ResponseBody: 응답 데이터를 HTML 파일이 아닌 순수 데이터(JSON, 텍스트 등)로 그대로 전달하겠다는 뜻
// @RestController: @Controller와 @ResponseBody의 개념이 합쳐진 것
@RestController // 요청을 받고 응답 데이터를 순수 데이터로 전달해주는 중간 다리 역할(영역 선언)
public class HelloController {

    // 메서드 단위
    // 특정 데이터(괄호 안의 값)이 불러와지면 이 어노테이션이 반응하여 신호를 끌어와 아래를 실행함
    @GetMapping("/hello") // 아래 무조껀 메서드를 포함해야 함
    public String hello() {

        return "Hello, world";
    }
}
