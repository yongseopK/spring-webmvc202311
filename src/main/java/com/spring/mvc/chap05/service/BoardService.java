package com.spring.mvc.chap05.service;

import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.BoardWriteResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardWriteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardWriteRepository repository;

    public BoardService(@Qualifier("DBRepo") BoardWriteRepository repository) {
        this.repository = repository;
    }

    public List<BoardWriteResponseDTO> getList() {
        return repository.findAll()
                .stream()
                .map(BoardWriteResponseDTO::new)
                .collect(Collectors.toList());

    }

    public void insertPost(BoardWriteRequestDTO dto) {
        repository.save(new Board(dto));
    }

    public Board retrieve(int boardNo) {
        return repository.findOne(boardNo);
    }

    public void deletePost(int bno) {
        repository.delete(bno);
    }
}






