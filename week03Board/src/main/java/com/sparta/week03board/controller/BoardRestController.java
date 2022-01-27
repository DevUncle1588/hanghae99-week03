package com.sparta.week03board.controller;

import com.sparta.week03board.models.Board;
import com.sparta.week03board.models.BoardRepository;
import com.sparta.week03board.models.BoardRequestDto;
import com.sparta.week03board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/api/posts")
    public List<Board> readPosts() {
//        System.out.println("-----------------------");
//        System.out.println(boardRepository.findAll());
//        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/posts/{targetId}")
    public Board readOne(@PathVariable Long targetId) {
        System.out.println(boardRepository.findAll());
        Optional<Board> board = boardRepository.findById(targetId);
        return board.get();
    }

    @PostMapping("/api/posts")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    @PutMapping("/api/board/{targetId}")
    public Long updateBoard(@PathVariable Long targetId, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(targetId, requestDto);
    }



//    @DeleteMapping("/api/posts/{id}")
//    public String delete(@PathVariable("id") Long id){
//        System.out.println("id = " + id);
////        boardRepository.deleteById(id);
//        boardService.deletePost(id);
//        return "redirect:/";
//    }

//    @DeleteMapping("/api/posts/{id}")
//    public Long deletePost(@PathVariable Long id){
//        boardRepository.deleteById(id);
//        return id;
//    }

    @DeleteMapping("/delete/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}
