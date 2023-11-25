package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Score;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import com.spring.mvc.chap04.entity.Grade;

/*
    서버가 클라이언트에 데이터를 전달할 때
    DB에 있는 모든 데이터를 전달하면
    민감한 정보나 보안상의 정보가 같이 전달될 수 있다.
    그래서 클라이언트에 보여줄 데이터만 선별해서 응답하돍
    이 클래스를 사용한다
 */

@RequiredArgsConstructor    // final만 골라서 초기화하는 생성자
@Getter @ToString @EqualsAndHashCode
public class ScoreResponseDTO {

    private final int stuNum;
    private final String maskingName; // 첫글자 빼고 *처리한 이름
    private final double average;
    private final Grade grade;

    public ScoreResponseDTO(Score score) {
        this.stuNum = score.getStuNum();
        this.maskingName = makeMaskingName(score.getName());
        this.average = score.getAverage();
        this.grade = score.getGrade();
    }

    private String makeMaskingName(String name) {
        String maskedName = String.valueOf(name.charAt(0));
        for (int i = 0; i < name.length() -1 ; i++) {
            maskedName += "*";
        }
        return maskedName;
    }
}
