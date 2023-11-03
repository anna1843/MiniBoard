package org.spring.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.spring.board.utill.BoardType;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardDto {

    private Long id; // id
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private int hit; // 조회수
    private BoardType boardType; // 유형
    private int isFile; // 파일여부
    private LocalDateTime CreateTime;
    private LocalDateTime UpdateTime;
}
