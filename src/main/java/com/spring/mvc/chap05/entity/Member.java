package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@ToString @Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private String          account;                // 계정
    private String          password;               // 패스워드
    private String          name;                   // 이름
    private String          email;                  // 이메일
    private Auth            auth;                   // 권한
    private LocalDateTime   regDate;                // 가입 날짜
    private String          sessionId;              // 세션 ID
    private LocalDateTime   limitTime;              // 세션 유지시간

    @Setter
    private String          profileImage;           // 프로필 사진 이미지 경로

    private LoginMethod     loginMethod;            // 로그인 방법

    public enum LoginMethod {
        COMMON, KAKAO, GOOGLE, NAVER
    }

}
