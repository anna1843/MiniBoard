package org.spring.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.board.dto.BoardDto;
import org.spring.board.entity.BoardEntity;
import org.spring.board.repository.BoardRepository;
import org.spring.board.utill.BoardType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public List<BoardDto> getboardList() {

        List<BoardDto> boardDtoList = new ArrayList<>(); // dto list를 새로 생성 -> 반환타입에 맞춤
        List<BoardEntity> boardEntityList = boardRepository.findAll(); // list찾아
        // entity로 가져온 list를 dto로 바꾸기 -> controller로 전달하고 이 결과값을 보내줌법
        for( BoardEntity board : boardEntityList){
            boardDtoList.add(toDto(board)); // return을 boradDto
            // list함수 중에 편하게 하는 .add
        }
        // 리스트는 여러개야, 리스트로 가져온 entity를 entity하나하나로 저장

        return boardDtoList;

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
    @Transactional
    private void updateHit(Long id) {
        boardRepository.updateHit(id);
    }
}
