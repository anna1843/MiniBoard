package org.spring.board.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.spring.board.utill.BoardType;

@Getter
@Setter
public class Search {
    // 검색을 위한 DTO
    // 요청을 받음
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private BoardType boardType; // 유형
}
