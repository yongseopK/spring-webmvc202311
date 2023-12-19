package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.response.BoardWriteResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardWriteMapper;
import lombok.RequiredArgsConstructor;
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

    public List<BoardWriteResponseDTO> getList(Search page) {
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

    public int getCount(Search page) {
        return repository.count(page);
    }
}






