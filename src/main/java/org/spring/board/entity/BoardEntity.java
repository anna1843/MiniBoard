package org.spring.board.entity;

import lombok.Builder;
import lombok.Getter;
import org.spring.board.utill.BaseEntity;
import org.spring.board.utill.BoardType;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @Column(nullable = false)
    private String title; // 제목

    @Column(length = 1000, nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private String writer; // 작성자

    private int hit; // 조회수

    @Enumerated(EnumType.STRING)
    private BoardType boardType; // 유형

    @Column(nullable = false)
    private int isFile; // 파일여부

    @Builder
    public void BoardCreate(String title, String content, String writer, BoardType boardType) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardType = boardType;
    } // 생성자를 이용한 방법

    @Builder
    public void BoardUpdate(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
