package com.practice.springbootpractice.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List; // List를 사용하기 위한 import

// 문제 해결
// 사용자가 브라우저 주소창에 "/item"이라는 주소로 들어왔을 때, "현재 판매중인 상품 목록입니다"
// 라는 순수 데이터를 리턴해주는 간단한 컨트롤러 코드를 작성하시오

@RestController // '@SpringBootApplication'에서 확인하여 등록시킬 컨트롤러 어노테이션을 작성
public class Test1 {

//    @GetMapping("/item") // 사용자가 "/item"이라는 주소로 들어왔으므로 "/item"주소를 매핑함
//    public String item() { // 문자열값을 반환하기에 String 사용
//
//        return "현재 판매 중인 상품 목록입니다.";
//    }

    // 문제 해결2
    // "/item"주소로 들어왔을 때 리스트 형식으로 상품들의 이름을 출력시키시오(JSON 형식 출력)

    @GetMapping("/item")
    public List<String> item() { // List<String>: List형식의 String변수형으로 반환하기 위함

        List<String> fruit = new ArrayList<>();

        fruit.add("사과");
        fruit.add("배");
        fruit.add("포도");

        return fruit;
    }
}
