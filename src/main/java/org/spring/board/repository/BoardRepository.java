package org.spring.board.repository;

import org.spring.board.entity.BoardEntity;
import org.spring.board.utill.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying
    @Query(value = "update BoardEntity b set b.hit=b.hit+1 where b.id=:id")
    void updateHit(@Param("id") Long id);

    Page<BoardEntity> findByBoardTypeAndTitleContaining(BoardType boardType, String search, Pageable pageable);
    Page<BoardEntity> findByBoardTypeAndContentContaining(BoardType boardType, String search, Pageable pageable);
    Page<BoardEntity> findByBoardType(BoardType boardType, Pageable pageable);
}
