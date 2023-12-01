package com.spring.mvc.chap05.dto;

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
