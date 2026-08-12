package com.practice.springbootpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 여러 줄 더럽게 쓸 필요 없이 어노테이션 하나로 파일 스캔을 돌림(Spring boot를 쓰는 이유 중 하나)
@SpringBootApplication // 자동화의 산물
public class SpringbootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPracticeApplication.class, args);
    }

}
