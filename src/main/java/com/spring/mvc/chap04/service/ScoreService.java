package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreMapper;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
    컨트롤러와 레파지토리 사이에 위치하여
    중간 로직을 처리하는 역할

    컨트롤러 -> 서비스 -> 레파지토리
 */

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreMapper repository;

    //private final ScoreRepository repository;

    //public ScoreService(@Qualifier("dbRepo") ScoreRepository repository) {
    //    this.repository = repository;
    //}

    // 목록 조회 중간처리
    /*
        컨트롤러는 DB에서 성적정보 리스트를
        조회해 오기를 바라고이따.
        그런데 DB는 민감한정보까지 모두 조회한다.
        그리고 컬럼명도 그대로 노출하기 때문에
        이 모든것을 숨기는 처리를 하고 싶다.
     */

    public List<ScoreResponseDTO> getList(String sort) {
        return repository.findAll(sort)
                .stream()
                .map(ScoreResponseDTO::new)
                .collect(Collectors.toList());

    }

    // 성적 입력 중간처리
    public boolean insertScore(ScoreRequestDTO dto) {
        return repository.save(new Score(dto));
    }

    // 삭제 중간 처리
    public boolean deleteScore(int stuNum) {
        return repository.delete(stuNum);
    }

    // 개별 상세조회 중간 처리
    public Score retrieve(int stuNum) {
        // 데이터 가공 ex) 글 제목 줄임처리, 조회수 상승, 반
        return repository.findOne(stuNum);
    }

    // 수정 완료 중간 처리
    public void updateScore(Score score, int kor, int eng, int math) {
        repository.updateScore(score.getStuNum(), kor, eng, math);
    }

    public void updateGrade(Score score, int stuNum) {
        int total = score.getKor() + score.getEng() + score.getMath();
        double average = total / 3.0;

        if(average  >= 90 ) score.setGrade(Grade.A);
        else if (average >= 80 ) score.setGrade(Grade.B);
        else if (average >= 70 ) score.setGrade(Grade.C);
        else if (average >= 60 ) score.setGrade(Grade.D);
        else score.setGrade(Grade.F);

        repository.updateGrade(stuNum, score.getGrade());
        System.out.println("service.grade = " + score.getGrade());
    }
}










