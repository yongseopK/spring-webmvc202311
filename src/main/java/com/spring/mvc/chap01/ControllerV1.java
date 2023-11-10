package com.spring.mvc.chap01;


// 컨트롤러 : 클라이언트의 요청을 받아서 처리 후 응답을 보내주는 역할

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller // 빈 등록 : 이 클래스의 객체 생성 관리는 스프링이 처리한다.
public class ControllerV1 {

    // 세부 요청처리는 메서드를 통해 등록
    @RequestMapping("/")
    public String home() {
        System.out.println("Welcome to my webpage!");

        // 반환문에는 어떤 jsp로 포워딩할지 경로를 적음
        return "index";
    }

    // /food 요청이 오면 food.jsp파일을 열기
    @RequestMapping("/food")
    public String food() {
        System.out.println("여긴 음식페이지");

        return "/chap01/food";
    }

    // =============== 요청 파라미터 읽기 (클라이언트가 보낸 정보) =============== //
    // == 1. HttpServletRequest객체 이용하기

    // ex : /person?name=kim&age=30
    @RequestMapping("/person")
    public String person(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "";
    }

    // == 2. @RequestParam 사용하기
    // ex : /major?stu=park&major=business&grade=3
    @RequestMapping("/major")
    public String major(String stu, @RequestParam("major") String mj, @RequestParam(defaultValue = "0") Integer grade) {
        System.out.println("stu = " + stu);
        System.out.println("mj = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }
}
