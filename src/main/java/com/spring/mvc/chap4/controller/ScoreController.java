package com.spring.mvc.chap4.controller;

/*
    # 컨트롤러
    - 클라이언트의 요청을 받아서 처리하고 응답을 제공하는 객체

    # 요청 URL Endpoint
    1.  학생의 성적정보 등록 폼 화면을 보여주고
        동시에 지금까지 저장되어있는 성적정보 목록을 조회
    - /score/list   :   GET

    2.  학생의 입력된 성적정보를 데이터베이스에 저장하는 요청
    - /score/register   :   POST

    3.  성적정보를 삭제하는 요청
    - /score/remove     :   GET or POST

    4.  성적정보 상세 요청
    - /score/detail     : GET
 */

import com.spring.mvc.chap4.entity.Score;
import com.spring.mvc.chap4.repository.ScoreRepository;
import com.spring.mvc.chap4.repository.ScoreRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor    // final이 붙은 필드를 초기화하는 생성자를 생성
//@AllArgsConstructor       // 모든 필드를 초기화하는 생성자를 생성
public class ScoreController {

    // 저장소에 의존되어 데이터 처리를 위임한다.
    private final ScoreRepository repository;

    //@Autowired  // 스프링에 등록된 빈을 자동주입
    // 생성자 주입을 사용하고 생성자가 단 하나 -> autowired 생략가능
    //public ScoreController(ScoreRepository repository) {
    //    this.repository = repository;
    //}

    // 1. 성적 폼 띄우기 + 목록조회
    // - jsp파일로 입력 폼 화면을 띄워줘야 함 (view 포워딩)
    // - 저장된 성적정보 리스트를 jsp에 보내줘야 함 (model에 데이터 전송)
    // - 저장된 성적정보 리스트를 어떻게 가져오느냐 from DB

    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/score/list GET");
        List<Score> scoreList = repository.findAll();
        System.out.println(scoreList);
        model.addAttribute("sList", scoreList);

        return "chap04/score-list";
    }

    // 2. 데이터베이스 저장 요청
    @PostMapping("/register")
    public String register() {
        System.out.println("/score/Register POST");
        return "";
    }

    // 3. 삭제요청
    @RequestMapping(value = "/remove", method = {RequestMethod.GET, RequestMethod.POST})
    public String remove(HttpServletRequest request) {
        System.out.printf("/score/remove %s", request);
        return "";
    }

    // 4. 상세정보 요청
    @GetMapping("/detail")
    public String detail() {
        System.out.println("/score/detail GET");
        return "";
    }
}


























