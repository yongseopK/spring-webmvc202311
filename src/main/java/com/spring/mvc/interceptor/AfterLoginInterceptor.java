package com.spring.mvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@Slf4j
public class AfterLoginInterceptor implements HandlerInterceptor {

    // 로그인 한 이후 비회원만 접근할 수 있는 페이지 접근 차단

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        log.info("after login interceptor execute");

        HttpSession session = request.getSession();

        if (session.getAttribute("login") != null) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
