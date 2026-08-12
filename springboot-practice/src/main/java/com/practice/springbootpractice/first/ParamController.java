package com.practice.springbootpractice.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamController {

    // /{변수명}: 모든 경우의 수를 하나하나 만들 수는 없기에 빈 칸으로 두어 사용자로 하여금 입력되는 값을 받아들이고 적용시킴
    @GetMapping("/games/{gameName}")
    public String getGame(@PathVariable String gameName) {
        // @PathVariable: 주소의 빈 칸 자리에 사용자가 쓴 값을 가져와 자바 변수에 담음
        return "당신이 검색한 게임은: " + gameName + "입니다";
    }
}
