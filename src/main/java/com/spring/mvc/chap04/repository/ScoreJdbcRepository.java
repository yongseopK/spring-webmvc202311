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
        String sql = "SELECT * FROM tbl_score";
        switch (sort) {
            case "num":
                sql += " ORDER BY stu_num";
                break;
            case "name":
                sql += " ORDER BY stu_name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }
        return template.query(sql, (rs, rn) -> new Score(rs));
    }

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score (stu_name, kor, eng, math, total, average, grade) VALUE (?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, score.getName(), score.getKor(), score.getEng(), score.getMath(), score.getTotal(), score.getAverage(), score.getGrade().toString()) == 1;
    }

    @Override
    public boolean delete(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num = ?";
        return template.update(sql, stuNum) == 1;
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";
        return template.queryForObject(sql, (rs, rn) -> new Score(rs), stuNum);
    }

    @Override
    public boolean update(int stuNum, int kor, int eng, int math) {
        return false;
    }
}
