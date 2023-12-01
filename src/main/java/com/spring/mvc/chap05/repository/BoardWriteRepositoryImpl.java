package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.springframework.stereotype.Repository;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Repository
public class BoardWriteRepositoryImpl implements BoardWriteRepository{
    private static final Map<Integer, Board> boardMap;

    private static int seq;

    static {
        boardMap = new HashMap<>();
    }
    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values())
                .stream()
                .sorted(comparing(Board::getBoardNo).reversed())
                .collect(toList())
                ;
    }

    @Override
    public Board findOne(int bno) {
        Board board = boardMap.get(bno);
        int viewCount = board.getViewCount();
        board.setViewCount(++viewCount);
        return board;
    }

    @Override
    public boolean save(Board board) {
        board.setBoardNo(++seq);
        if(boardMap.containsKey(board.getBoardNo())) return false;

        boardMap.put(board.getBoardNo(), board);
        return true;
    }

    @Override
    public boolean delete(int bno) {
        if(!boardMap.containsKey(bno)) return false;

        boardMap.remove(bno);
        return true;
    }
}
