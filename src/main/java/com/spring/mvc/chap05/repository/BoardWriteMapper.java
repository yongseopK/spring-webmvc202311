package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardWriteMapper {
    List<Board> findAll(Search page);

    Board findOne(int bno);

    void upViewCount(int bno);

    boolean save(Board board);

    boolean delete(int bno);

    // 총 게시물 수 구하기
    int count(Search search);
}
