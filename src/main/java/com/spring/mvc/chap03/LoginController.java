package com.spring.mvc.chap03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hw")
public class LoginController {
    /*
        1번요청: 로그인 폼 화면 열어주기
        - 요청 URL : /hw/s-login-form : GET
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-form.jsp
        - html form: 아이디랑 비번을 입력받으세요.
     */

    @GetMapping("/s-login-form")
    public String viewForm() {
        return "chap03/s-form";
    }




     /*
        2번요청: 로그인 검증하기
        - 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공
        - 요청 URL : /hw/s-login-check : POST
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-result.jsp
        - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우
     */
     @PostMapping("/s-login-check")
     public ModelAndView result(String id, String password) {
         String isLogin = "0";
         ModelAndView mv = new ModelAndView("chap03/s-result");
         System.out.println(id);
         System.out.println(password);
         //mv.addObject("id", id);
         //mv.addObject("password", password);
         if(!id.equals("grape111")) {
            isLogin = "1";
         } else if(!password.equals("ggg9999")) {
             isLogin = "2";
         } else if (id.equals("grape111") && password.equals("ggg9999")) {
             isLogin = "3";
         }

         System.out.println("isLogin = " + isLogin);

         mv.addObject("state", isLogin);
         mv.addObject("id", id);
         return mv;
     }
}
