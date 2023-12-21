package com.spring.mvc.chap05.dto.request;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyModifyRequestDTO {

    @NotNull
    private Long rno;       // 수정할 댓글 번호

    @NotBlank
    private String text;    // 수정할 댓글 내용

    @NotNull
    private Long bno;       // 수정 후 수정된 목록 조회용

    // dto를 엔터티로 바꿔주는 메서드
    public Reply toEntity() {
        return Reply.builder()
                .replyNo(this.rno)
                .replyText(this.text)
                .boardNo(this.bno)
                .build();
    }

}
