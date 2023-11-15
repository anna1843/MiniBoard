package org.spring.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.board.dto.BoardDto;
import org.spring.board.entity.BoardEntity;
import org.spring.board.repository.BoardRepository;
import org.spring.board.utill.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private  final BoardRepository boardRepository;

    @Transactional
    public Long postBoardWrite(BoardDto boardDto) {

        BoardEntity boardEntity = new BoardEntity(); // 게시판 작성할라고 entity객체 생성
        boardEntity.BoardCreate(boardDto.getTitle(), boardDto.getContent(), boardDto.getWriter(), boardDto.getBoardType()); // 셍성자를 이용해서 작성기능을 만들었다.

        Long result = boardRepository.save(boardEntity).getId(); // 생성된 boardid
        return  result;
    }

    // 페이징 + 검색 게시판조회
    @Transactional
    public Page<BoardDto> getboardPagingTypeList(BoardType boardType, String select, String search, Pageable pageable) {

        Page<BoardEntity> boardEntities = null;

//      검색기능
        if(boardType != null){
            if (search != null && !search.isEmpty()) {
                if (select.equals("title")) {
                    boardEntities = boardRepository.findByBoardTypeAndTitleContaining(boardType, search, pageable);
                    // 제목 검색
                } else if (select.equals("content")) {
                    boardEntities = boardRepository.findByBoardTypeAndContentContaining(boardType, search, pageable);
                    // 내용 검색
                }
            }else {
                boardEntities = boardRepository.findByBoardType(boardType, pageable);
            }
        }else{
//            boardType이 null -> 전체
            boardEntities = boardRepository.findAll(pageable);
        }

        // 페이징 처리 위한 임시 작성ㅜ
        return boardEntities.map(BoardDto::toDto);

    }

    @Transactional
    public Long postBoardUpdate(BoardDto boardDto, Long id) {

        BoardEntity boardEntity = new BoardEntity(); // 게시판 작성할라고 entity객체 생성
        boardEntity.BoardUpdate(id, boardDto.getTitle(), boardDto.getContent(), boardDto.getWriter()); // 셍성자를 이용해서 수정기능을 만들었다.

        Long result = boardRepository.save(boardEntity).getId(); // 생성된 boardid

        return result;
    }

    // 재사용을 위한 method
    private BoardDto toDto(BoardEntity board){
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .hit(board.getHit())
                .boardType(board.getBoardType())
                .CreateTime(board.getCreateTime())
                .UpdateTime(board.getUpdateTime())
                .build();
    }

    @Transactional
    public Long postBoardDelete(Long id) {

        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        if(boardEntity.getId() != null){
            Long result = boardEntity.getId();
            boardRepository.delete(boardEntity);
            return result;
        }
        return 0L;

    }

    @Transactional
    public List<BoardDto> getBoardDetail(Long id) {
        updateHit(id); // 조회수 증가시키기

        List<BoardDto> boardDtoList = new ArrayList<>();
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException("조회할 게시글이 존재하지 않습니다.");
        });
        boardDtoList.add(toDto(boardEntity));

        return boardDtoList;
    }

    // 조회수 증가
    private void updateHit(Long id) {
        boardRepository.updateHit(id);
    }


}
