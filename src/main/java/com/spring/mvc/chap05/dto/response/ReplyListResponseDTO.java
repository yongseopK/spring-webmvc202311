package com.spring.mvc.chap05.dto.response;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyListResponseDTO {

    private int count;  // 총 댓글
    private List<ReplyDetailResponseDTO> replies;   // 실제 댓글 리스트
}
