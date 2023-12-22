package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@ToString @Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private String account;     // 계정
    private String password;    // 패스워드
    private String name;        // 이름
    private String email;       // 이메일
    private Auth auth;           // 권한
    private LocalDateTime regDate;

}
