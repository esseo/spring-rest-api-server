package com.es.prototype.service;

import com.es.prototype.dto.BoardDto;

import java.util.List;

public interface BoardService {

    public List<BoardDto> findAll() throws Exception;

    public BoardDto findById(Long id) throws Exception;

    public BoardDto save(BoardDto boardDto) throws Exception;

    public BoardDto update(BoardDto boardDto, Long id) throws Exception;

    public void delete(Long id) throws Exception;

}
