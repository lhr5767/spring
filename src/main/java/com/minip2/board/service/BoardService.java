package com.minip2.board.service;


import com.minip2.board.dto.BoardDTO;
import com.minip2.board.entity.Board;
import com.minip2.board.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    //dto를 entity로 변환
    default Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board =Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }
}
