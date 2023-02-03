package com.es.prototype.service;

import com.es.prototype.dto.BoardDto;
import com.es.prototype.entity.Board;
import com.es.prototype.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    public List<BoardDto> findAll() {
        ModelMapper mapper = new ModelMapper();
        return boardRepository.findAll().stream().map(board -> mapper.map(board, BoardDto.class)).collect(Collectors.toList());
    }

    public BoardDto findById(Long id) {
        ModelMapper mapper = new ModelMapper();

        Board board =  boardRepository.findById(id).orElseThrow(NullPointerException::new);

        // 조회수 증가
        board.setHit(board.getHit() + 1);
        boardRepository.save(board);

        return mapper.map(board, BoardDto.class);
    }

    public BoardDto save(BoardDto boardDto) {
        ModelMapper mapper = new ModelMapper();
        Board board = mapper.map(boardDto, Board.class);
        return mapper.map(boardRepository.save(board), BoardDto.class);
    }

    public BoardDto update(BoardDto boardDto, Long id) {
        ModelMapper mapper = new ModelMapper();
        Board board = mapper.map(boardDto, Board.class);
        board.setId(id);
        return mapper.map(boardRepository.save(board), BoardDto.class);
    }

    public void delete(Long id) {
        Board board = new Board();
        board.setId(id);

        boardRepository.delete(board);
    }


}
