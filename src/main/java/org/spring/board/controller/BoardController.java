package org.spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.spring.board.dto.BoardDto;
import org.spring.board.service.BoardService;
import org.spring.board.utill.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 게시판 작성하기
    @PostMapping("/create") // 생성은 post
    public Map<String, Object> postBoardWrite(@RequestBody BoardDto boardDto){
        Long result = boardService.postBoardWrite(boardDto);
        Map<String, Object> map = new HashMap<>();
        map.put("board", result); // map으로 board 라는 key를 추가
        return map;
    }

    // 게시판 보여주기
    // Boardtype에 따라서 일반, 비밀, 익명게시판
    @GetMapping("/list")
    public Map<String, Object> getBoardTypeList(
                                            @RequestParam(value = "boardType", required = false) BoardType boardType,
                                            @RequestParam(value = "select", required = false) String select,
                                            @RequestParam(value = "search", required = false) String search,
                                            @PageableDefault(sort = "id", size = 20, direction = Sort.Direction.ASC) Pageable pageable){
        Map<String, Object> map = new HashMap<>();
        Page<BoardDto> boardPagingList = boardService.getboardPagingTypeList(boardType, select, search, pageable);
        map.put("boardList", boardPagingList);
        return map;
    }

    // 게시판 수정하기
    @PostMapping("/update/{id}") // 수정은 post
    public Map<String, Object> postBoardUpdate(@RequestBody BoardDto boardDto, @PathVariable("id") Long id){
        Long result = boardService.postBoardUpdate(boardDto, id);
        Map<String,Object> map = new HashMap<>();
        map.put("boardUpdate", result);
        return map;
    }

    // 게시판 삭제하기
    @PostMapping("/delete/{id}")
    public Map<String, Object> postBoardDelete(@PathVariable("id") Long id){
        Long result = boardService.postBoardDelete(id);
        Map<String, Object> map = new HashMap<>();
        map.put("boardDelete", result);
        return map;
    }

    // 게시판 상세보기
    @GetMapping("/detail/{id}")
    public Map<String, Object> getBoardDetail(@PathVariable("id") Long id){

        List<BoardDto> boardList = boardService.getBoardDetail(id);
        Map<String, Object> map = new HashMap<>();
        map.put("boardDetail", boardList);
        return map;
    }
}
