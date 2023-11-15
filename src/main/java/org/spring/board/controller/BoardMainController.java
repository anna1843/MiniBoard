package org.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardMainController {

    @GetMapping("/list")
    public String boardList(){
        return "/board/list";
    }
    @GetMapping("/detail/{id}")
    public String boardDetail(@PathVariable("id") Long id){
//        model.addAttribute("id",id);
        return "/board/detail";
    }
}
