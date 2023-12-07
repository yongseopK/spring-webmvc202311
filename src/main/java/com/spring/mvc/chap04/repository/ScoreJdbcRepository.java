package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("dbRepo")
@RequiredArgsConstructor
public class ScoreJdbcRepository implements ScoreRepository{

    private final JdbcTemplate template;

    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        return Collections.emptyList();
    }

    @Override
    public boolean save(Score score) {
        return false;
    }

    @Override
    public boolean delete(int stuNum) {
        return false;
    }

    @Override
    public Score findOne(int stuNum) {
        return null;
    }

    @Override
    public boolean update(int stuNum, int kor, int eng, int math) {
        return false;
    }
}
