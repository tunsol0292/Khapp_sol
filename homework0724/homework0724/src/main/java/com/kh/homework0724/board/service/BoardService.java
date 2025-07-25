package com.kh.homework0724.board.service;

import com.kh.homework0724.board.mapper.BoardMapper;
import com.kh.homework0724.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    public int insert(BoardVo boardVo) {
        return boardMapper.insert(boardVo);
    }

    public List<BoardVo> boardList() {
        return boardMapper.boardList();
    }

    public BoardVo boardOneByNo(int no) {
        return boardMapper.boardOneByNo(no);
    }

    public int delete(int no) {
        return boardMapper.delete(no);
    }

    public int update(BoardVo boardVo) {
        return boardMapper.update(boardVo);
    }
}
