package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getInt("board_no");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.regDateTime = rs.getTimestamp("regDateTime").toLocalDateTime();
        this.viewCount = rs.getInt("view_count");
    }

    private void timeFormat() {
        this.regDateTime = LocalDateTime.now();
    }


    private void convertInputData(BoardWriteRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
