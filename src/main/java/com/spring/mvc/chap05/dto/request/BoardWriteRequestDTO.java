package com.spring.mvc.chap05.dto.request;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequestDTO {
    private String title;
    private String content;
}
