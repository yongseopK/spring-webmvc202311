package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.BoardWriteResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;
    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String select(Model model) {
        List<BoardWriteResponseDTO> dtoList = service.getList();
        model.addAttribute("bList", dtoList);

        return "chap05/list";
    }

    // 2. 글쓰기 화면요청 (/board/write : GET)
    @GetMapping("/write")
    public String writePost() {
        System.out.println("글쓰기 페이지로 이동");
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
    public String detail(int bno, Model model) {
        System.out.println("볼 게시물 : " + bno);

        Board board = service.retrieve(bno);

        BoardWriteResponseDTO dto = new BoardWriteResponseDTO(board);


        model.addAttribute("b", dto);

        return "chap05/detail";
    }
}





















