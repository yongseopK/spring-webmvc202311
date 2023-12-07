package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Repository("mRepo")
public class ScoreRepositoryImpl implements ScoreRepository {

    // 인메모리 저장공간 해시맵
    // key : 학번, value : 성적정보
    private static final Map<Integer, Score> scoreMap;

    // 학번을 생성할 일련번호
    private static int seq;

    // 객체 초기화는 직접하는것보다 주입받거나 생성자를 통해 처리하는게 좋다.
    static {
        scoreMap = new HashMap<>();
        Score s1 = new Score("뽀로로", 100, 88, 33, ++seq, 0, 90.0, Grade.A);
        Score s2 = new Score("춘식이", 33, 99, 11, ++seq, 0, 65.0, Grade.C);
        Score s3 = new Score("쿠로미", 66, 55, 22, ++seq, 0, 44.0, Grade.F);

        scoreMap.put(s1.getStuNum(), s1);
        scoreMap.put(s2.getStuNum(), s2);
        scoreMap.put(s3.getStuNum(), s3);
    }

    @Override
    public List<Score> findAll() {
        // 맵에있는 모든 성적정보를 꺼내서 리스트에 담아라
        //List<Score> temp = new ArrayList<>();
        //for (Integer key : scoreMap.keySet()) {
        //    Score score = scoreMap.get(key);
        //    temp.add(score);
        //}

        return new ArrayList<>(scoreMap.values())
                .stream()
                .sorted(comparing(Score::getStuNum))
                .collect(toList())
                ;

    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparing = comparing(Score::getStuNum);

        switch (sort) {
            case "num":
                comparing = comparing(Score::getStuNum);
                break;
            case "name":
                comparing = comparing(Score::getName);
                break;
            case "avg":
                comparing = comparing(Score::getAverage).reversed();
                break;
        }

        List<Score> collect = new ArrayList<>(scoreMap.values())
                .stream()
                .sorted(comparing)
                .collect(toList());
        return collect;
    }

    @Override
    public boolean save(Score score) {
        // 학번 넣어주기
        score.setStuNum(++seq);

        // 중복된 학번을 전달할 경우
        if(scoreMap.containsKey(score.getStuNum())) return false;


        scoreMap.put(score.getStuNum(), score);
        return true;
    }

    @Override
    public boolean delete(int stuNum) {
        // 없는 학번을 전달받을 경우
        if(!scoreMap.containsKey(stuNum)) return false;

        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public boolean update(int stuNum, int kor, int eng, int math) {
        int total = kor + eng + math;
        double avg = total / 3.0;
        for (Score value : scoreMap.values()) {
            if(stuNum == value.getStuNum()) {
                value.setKor(kor);
                value.setEng(eng);
                value.setMath(math);
                value.setTotal(total);
                value.setAverage(avg);
            }
        }

        return true;
    }
}
