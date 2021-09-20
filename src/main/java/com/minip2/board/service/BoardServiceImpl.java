package com.minip2.board.service;

import com.minip2.board.dto.BoardDTO;
import com.minip2.board.dto.PageRequestDTO;
import com.minip2.board.dto.PageResultDTO;
import com.minip2.board.entity.Board;
import com.minip2.board.entity.Member;
import com.minip2.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository; //autowired 보다 이방식을 선호

    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board = dtoToEntity(dto);

        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0],(Member) en[1],(Long) en[2]
        ));

        Page<Object[]> result = boardRepository.getBoarWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0],(Member) arr[1],(Long) arr[2]);
    }
}
