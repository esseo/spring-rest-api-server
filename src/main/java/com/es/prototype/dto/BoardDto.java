package com.es.prototype.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    private Long id;

    private String title;

    private String contents;

    private String name;

    private int hit;

    private BoardDto() {
        this.hit = 0;
    }

}
