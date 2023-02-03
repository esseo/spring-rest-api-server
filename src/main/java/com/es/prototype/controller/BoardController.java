package com.es.prototype.controller;

import com.es.prototype.dto.BoardDto;
import com.es.prototype.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {

    @Autowired
    private final BoardServiceImpl boardService;

    @GetMapping("/boards")
    public ResponseEntity<Map> findAll() {

        Map responseMap = new HashMap<>();

        try {
            List<BoardDto> boards = boardService.findAll();
            responseMap.put("data", boards);

            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Map> findById(@PathVariable Long id) {

        Map responseMap = new HashMap<>();

        try {
            BoardDto board = boardService.findById(id);

            responseMap.put("data", board);

            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/board")
    public ResponseEntity<Map> saveBoard(@RequestBody BoardDto boardDto) {

        Map responseMap = new HashMap<>();

        try {
            BoardDto board = boardService.save(boardDto);
            responseMap.put("data", board);

            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<Map> updateBoard(@PathVariable Long id,  @RequestBody BoardDto boardDto) {

        Map responseMap = new HashMap<>();

        try {
            BoardDto board = boardService.update(boardDto, id);

            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Map> deleteBoard(@PathVariable Long id) {

        Map responseMap = new HashMap<>();

        try {
            boardService.delete(id);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
