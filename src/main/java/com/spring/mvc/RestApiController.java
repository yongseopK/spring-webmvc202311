package com.spring.mvc;

import com.spring.mvc.chap06.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/rests")
@Slf4j
public class RestApiController {

    @GetMapping("/hello")
    @ResponseBody   // 클라이언트에게 jsp가 아닌 json을 보내는 방법
    public String hello() {
        log.info("/rest/hello GET!");
        return "hello apple banana!!";
    }

    @GetMapping("/food")
    @ResponseBody
    public List<String> food() {
        return List.of("짜장면", "볶음밥", "탕수육");
    }

    @GetMapping("/person")
    public Person person() {
        return new Person("334", "말포이", 50);
    }
}
