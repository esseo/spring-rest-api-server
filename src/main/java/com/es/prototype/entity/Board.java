package com.es.prototype.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "contents", nullable = false, length = 4000)
    private String contents;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "hit", nullable = false)
    private int hit;
}
