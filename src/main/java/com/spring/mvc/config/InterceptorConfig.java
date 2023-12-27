package com.spring.mvc.config;

import com.spring.mvc.interceptor.AfterLoginInterceptor;
import com.spring.mvc.interceptor.BoardInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 만든 인터셉터들을 스프링 컨텍스트에 등록하는 설정파일

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AfterLoginInterceptor afterLoginInterceptor;
    private final BoardInterceptor boardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그인 후 비회원 전용페이지 접근 차단 인터셉터 설정
        registry
                .addInterceptor(afterLoginInterceptor)  // 어떤 인터셉터를 등록할것인가
                .addPathPatterns("/members/sign-in", "/members/sign-up")  // 어떤 요청에서 인터셉터를 발생시킬 것인가
                ;

        // 게시판 인터셉터 설정
        registry
                .addInterceptor(boardInterceptor)
                .addPathPatterns("/board/*")
                .excludePathPatterns("/board/list", "/board/detail")      // 인터셉터 발동 제외경로
                ;
    }
}
