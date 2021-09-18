package com.minip2.board.repository;

import com.minip2.board.entity.Board;
import com.minip2.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1,100).forEach(i-> {

            //member 테스트 에서 만든 객체 이용
            Member member = Member.builder().email("usr"+i+"@aaa.com").build();

            Board board = Board.builder()
                    .title("Title--"+i)
                    .content("Content--"+i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }
}
