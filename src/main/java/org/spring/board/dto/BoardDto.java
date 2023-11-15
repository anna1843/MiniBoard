package org.spring.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.spring.board.entity.BoardEntity;
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

    // 페이징을 위한 임시방편 작성
    public static BoardDto toDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .hit(boardEntity.getHit())
                .boardType(boardEntity.getBoardType())
                .CreateTime(boardEntity.getCreateTime())
                .UpdateTime(boardEntity.getUpdateTime())
                .build();
    }


    // dto -> 클라이언트에서 서버로 요청을 할 때 데이터를 담는 그릇
    // controller <-> 클라이언트(고객)

    // request: 요청 나 글쓸꺼야 ( 글 내용, 제목)
    // response: 응답  ㅇ

    // 필요한 데이터가 있다. -> dto를 만들어.
}
