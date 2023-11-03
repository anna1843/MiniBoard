package org.spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.spring.board.dto.BoardDto;
import org.spring.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
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

    // 게시판 전체 보여주기
    // 페이징, 검색 추가하기
    @GetMapping("/list")
    public Map<String, Object> getBoardList(){
        Map<String, Object> map = new HashMap<>();
        List<BoardDto> boardList = boardService.getboardList();
        map.put("boardList", boardList);
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
