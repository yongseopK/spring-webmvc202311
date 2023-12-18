package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper;

    // 댓글 목록 조회
    public List<Reply> getList(long boardNo) {

        return replyMapper.findAll(boardNo);
    }
}
