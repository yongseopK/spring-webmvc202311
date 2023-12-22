package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    // 회원 가입
    boolean save(Member member);

    // 회원 정보 단일 조회
    Member findMember(String account);

    /**
     * 중복체크(account, email) 기능
     * @param type 중복을 검사할 내용 (account, email)
     * @param keyword 중복 검사 입력값 (ex: abc@email.com)
     * @return 중복이면 true, 중복이 아니면 false
     */
    boolean isDuplicate(@Param("type") String type, @Param("keyword") String keyword);

}
