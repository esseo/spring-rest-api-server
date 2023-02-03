package com.es.prototype.repository;

import com.es.prototype.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Override
    Optional<Board> findById(Long id);


}
