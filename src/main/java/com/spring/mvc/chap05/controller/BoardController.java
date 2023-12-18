package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.BoardWriteResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService service;
    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String select(@ModelAttribute("s") Search page, Model model) {
        log.info("/board/list : GET!");
        log.debug("{}", page);

        List<BoardWriteResponseDTO> dtoList = service.getList(page);

        // 페이징 계산 알고리즘 적용
        PageMaker maker = new PageMaker(page, service.getCount(page));

        model.addAttribute("bList", dtoList);
        model.addAttribute("maker", maker);
        //model.addAttribute("s", page);
        return "chap05/list";
    }

    // 2. 글쓰기 화면요청 (/board/write : GET)
    @GetMapping("/write")
    public String writePost() {
        log.info("글쓰기 페이지로 이동");
        return "chap05/write";
    }
    // 3. 글쓰기 등록요청 (/board/write : POST)
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto) {
        service.insertPost(dto);
        return "redirect:/board/list";
    }
    // 4. 글 삭제 요청 (/board/delete : GET)
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("삭제할 게시물 : " + bno);

        service.deletePost(bno);
        return "redirect:/board/list";
    }
    // 5. 글 상세보기 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(@ModelAttribute("s") Search search, @RequestParam("bno") int boardNo, Model model) {
        System.out.println("볼 게시물 : " + boardNo);

        Board board = service.retrieve(boardNo);
        service.upViewCountt(boardNo);
        BoardWriteResponseDTO dto = new BoardWriteResponseDTO(board);


        model.addAttribute("b", dto);

        return "chap05/detail";
    }
}





















