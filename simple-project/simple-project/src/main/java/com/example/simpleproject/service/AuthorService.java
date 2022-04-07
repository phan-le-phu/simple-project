package com.example.simpleproject.service;

import com.example.simpleproject.dto.AuthorDto;

import java.text.ParseException;
import java.util.List;

public interface AuthorService {
    void insertAuthor(AuthorDto authorDto) throws ParseException;
    List<AuthorDto> findAllAuthor();
}
