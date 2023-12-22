package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback
public class ReplyMapperTest {

    @Autowired
    BoardWriteMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;
    
    @Test
    @DisplayName("게시물 100개를 등록하고 랜덤으로 1000개의 댓글을 게시물에 등록한다.")
     void bulkInsertTest() {
        //given
        for (int i = 1; i <= 100; i++) {
            Board b = Board.builder()
                    .title("재밌는 글 " + i)
                    .content("응 개노잼~ " + i)
                    .build();
            boardMapper.save(b);
        }

        for (int i = 0; i <= 1000 ; i++) {
            Reply r = Reply.builder()
                    .replyText("하하호호히히~ " + i)
                    .replyWriter("잼민이 " + i)
                    .boardNo((long) (Math.random() * 100 + 1))
                    .build();

            replyMapper.save(r);
        }
        //when
    
        //then
    }

    @Test
    @DisplayName("77번 게시물의 댓글목록을 조회했을 때, 결과 리스트의 사이즈는 10이어야한다.")
    void findAllTest() {
        //given
        long boardNo = 77L;
        //when
        List<Reply> replyList = replyMapper.findAll(boardNo, new Page(1, 5));

        //then
        assertEquals(10, replyList.size());
        assertEquals("잼민이 113", replyList.get(0).getReplyWriter());
    }

    @Test
    @DisplayName("77번 게시물의 433번 댓글을 삭제하면 433번 댓글은 조회되지 않을 것이고" +
    " 77번을 전제조회하면 리스트의 사이즈는 9여야함.")
    void deleteTest() {
        //given
        long boardNo = 77;
        long replyNo = 433;
        //when
        replyMapper.delete(replyNo);
        Reply reply = replyMapper.findOne(replyNo);
        //then
        assertNull(reply);
        assertEquals(9, replyMapper.findAll(boardNo, new Page(1, 5)).size());
    }

    @Test
    @DisplayName("103번 댓글의 댓글 내용을 수정하면, 다시 조회했을 때 수정된 내용이 조회된다.")
    void modifyTest() {
        //given
        long replyNo = 103;
        String newReplyText = "수정수정댓글";
        //when
        Reply r = Reply.builder()
                .replyText(newReplyText)
                .replyNo(replyNo)
                .build();

        boolean flag = replyMapper.modify(r);

        //then
        assertTrue(flag);
        Reply foundReply = replyMapper.findOne(replyNo);
        assertEquals(newReplyText, foundReply.getReplyText());

        System.out.println("\n\n\n");
        System.out.println("foundReply = " + foundReply);
        System.out.println("\n\n\n");
    }


    
}