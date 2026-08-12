package com.practice.springbootpractice.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

//문제 해결
// 사용자가 주소창에 "/games"로 들어왔을 때, 본인이 좋아하는 게임 목록(최소 3개 이상)을
// 문자열 리스트 형태로 리턴하는 컨트롤러 클래스를 작성하시오

//조건
//1. 클래스 이름을 "GameController"로 할 것
//2. 어노테이션 두 개(@Restcontroller, @Getmapping)을 알맞게 붙일 것
//3. 메서드 이름은 자유, 리턴 타입은 List<String>으로 할 것
//4. 리스트 안에 게임 이름을 3개 이상 ".add()"로 채워서 리턴할 것

@RestController
public class GameController {

    @GetMapping("/games")
    public List<String> games() {

        List<String> game = new ArrayList<>();

        game.add("리그 오브 레전드");
        game.add("배틀그라운드");
        game.add("오버워치");

        return game;
    }
}
