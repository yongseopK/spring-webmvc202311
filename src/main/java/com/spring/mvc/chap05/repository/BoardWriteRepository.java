package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.entity.Board;

import java.util.List;

public interface BoardWriteRepository {
    List<Board> findAll(Search page);

    Board findOne(int bno);

    boolean save(Board board);

    boolean delete(int bno);

}
