package com.spring.mvc.chap05.service;

import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.BoardWriteResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardWriteMapper;
import com.spring.mvc.chap05.repository.BoardWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardWriteMapper repository;
    // private final BoardWriteRepository repository;

    //public BoardService(@Qualifier("DBRepo") BoardWriteRepository repository) {
    //    this.repository = repository;
    //}

    public List<BoardWriteResponseDTO> getList(Page page) {
        return repository.findAll(page)
                .stream()
                .map(BoardWriteResponseDTO::new)
                .collect(Collectors.toList());

    }

    public void insertPost(BoardWriteRequestDTO dto) {
        repository.save(new Board(dto));
    }

    public Board retrieve(int boardNo) {
        Board foundBoard = repository.findOne(boardNo);

        if (foundBoard == null) {
            repository.upViewCount(boardNo);
        }

        return foundBoard;
    }

    public void upViewCountt(int boardNo) {
        repository.upViewCount(boardNo);
    }


    public void deletePost(int bno) {
        repository.delete(bno);
    }

    public int getCount() {
        return repository.count();
    }
}






