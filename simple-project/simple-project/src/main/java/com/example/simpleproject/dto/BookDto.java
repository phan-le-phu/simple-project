package com.example.simpleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {
    private String name;
    private Long authorId;
    private String authorName;
}
