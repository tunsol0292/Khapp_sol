package com.kh.homework0724.board.api;

import com.kh.homework0724.board.service.BoardService;
import com.kh.homework0724.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class BoardApiController {

    private final BoardService boardService;

    // 게시물 등록하기
    @PostMapping
    public ResponseEntity<Integer> insertBoard(@RequestBody BoardVo boardVo){
        int result = boardService.insert(boardVo);
        return ResponseEntity.ok().body(result);
    }

    // 게시물 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardVo>> boardList() {
        List<BoardVo> boardVoList = boardService.boardList();
        return ResponseEntity.ok().body(boardVoList);
    }

    // 게시물 단건 조회
    @GetMapping("{no}")
    public ResponseEntity<BoardVo> boardListByNo(@PathVariable int no) {
        BoardVo boardVo = boardService.boardOneByNo(no);
        return ResponseEntity.ok().body(boardVo);
    }

    // 게시물 삭제 (소프트 딜리트)
    @DeleteMapping("{no}")
    public ResponseEntity<Integer> deleteBoard(@PathVariable int no) {
        int result = boardService.delete(no);
        return ResponseEntity.ok().body(result);
    }

    // 게시물 수정
    @PutMapping
    public ResponseEntity<Integer> updateBoard(@RequestBody BoardVo boardVo) {
        int result = boardService.update(boardVo);
        return ResponseEntity.ok().body(result);
    }

}
