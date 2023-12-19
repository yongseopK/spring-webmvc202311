package com.spring.mvc.chap05.dto.response;

import com.spring.mvc.chap05.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor    // final만 골라서 초기화하는 생성자
@Getter
@ToString
@EqualsAndHashCode
public class BoardWriteResponseDTO {
    private final String title;
    private final String content;

    private final  String shortTitle;
    private final  String shortContent;
    private final  String regDateTime;
    private final int viewCount;
    private final int boardNo;

    public BoardWriteResponseDTO(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();

        this.shortTitle = shortTitle(board.getTitle());
        this.shortContent = shortContent(board.getContent());
        this.regDateTime = timeFormatter(board.getRegDateTime());
        this.viewCount = board.getViewCount();
        this.boardNo = board.getBoardNo();
    }

    private String shortTitle(String name) {
        if (name.length() > 7) {
            return name.substring(0, 7) + "...";
        } else {
            return name;
        }
    }

    private String shortContent(String content) {
        if (content.length() > 30) {
            return content.substring(0, 30) + "...";
        } else {
            return content;
        }
    }

    private String timeFormatter(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(formatter);
    }
}







