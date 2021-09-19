package com.minip2.board.service;


import com.minip2.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("Test")
                .content("testCon")
                .writerEmail("usr1@aaa.com") //DB에 저장된 이메일
                .build();

        Long bno = boardService.register(dto);

    }
}
