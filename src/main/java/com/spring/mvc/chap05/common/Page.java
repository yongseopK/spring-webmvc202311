package com.spring.mvc.chap05.common;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Page {
    private int pageNo;     // 클라이언트가 보낸 페이지번호
    private int amount;     // 클라이언트가 보낸 목록 게시물 수

    public Page() {
        this.pageNo = 1;
        this.amount = 6;
    }



    /*
        만약에 한 페이지에 게시물을 10개씩 뿌린다고 가정하면

        1페이지 -> LIMIT 0, 10
        1페이지 -> LIMIT 10, 10
        1페이지 -> LIMIT 20, 10

        만약에 한 페이지에 게시물을 6개씩 뿌린다고 가정하면

        1페이지 -> LIMIT 0, 6
        1페이지 -> LIMIT 6, 6
        1페이지 -> LIMIT 12, 6

        만약에 한 페이지에 게시물을 n개씩 뿌린다고 가정하면

        1페이지 -> LIMIT 0, N
        1페이지 -> LIMIT 6, N
        1페이지 -> LIMIT 12, N
        M페이지 -> LIMIT (M - 1) * N, N
     */

    public int getPageStart() {
        return (pageNo - 1) * amount;
    }
}
