package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import lombok.*;
import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성일자시간

    public Board(BoardWriteRequestDTO dto) {
        convertInputData(dto);
        timeFormat();
        this.boardNo = getBoardNo();
        this.viewCount = getViewCount();
    }

    private void timeFormat() {
        this.regDateTime = LocalDateTime.now();;
    }


    private void convertInputData(BoardWriteRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
