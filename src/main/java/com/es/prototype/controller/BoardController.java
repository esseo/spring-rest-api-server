package com.es.prototype.controller;

import com.es.prototype.dto.BoardDto;
import com.es.prototype.exceptions.CustomException;
import com.es.prototype.exceptions.ErrorCode;
import com.es.prototype.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);

    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Map> findById(@PathVariable Long id) throws Exception{

        Map responseMap = new HashMap<>();

        try {
            BoardDto board = boardService.findById(id);
            responseMap.put("data", board);
        } catch(NoSuchElementException e1) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        } catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<Map> saveBoard(@RequestBody BoardDto boardDto) {

        Map responseMap = new HashMap<>();

        try {
            BoardDto board = boardService.save(boardDto);
            responseMap.put("data", board);

        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<Map> updateBoard(@PathVariable Long id,  @RequestBody BoardDto boardDto) {

        Map responseMap = new HashMap<>();

        try {
            BoardDto board = boardService.update(boardDto, id);
            responseMap.put("board", board);

        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Map> deleteBoard(@PathVariable Long id) {

        Map responseMap = new HashMap<>();

        try {
            boardService.delete(id);

        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
