package com.example.simpleproject.service.impl;

import com.example.simpleproject.converter.AuthorConverter;
import com.example.simpleproject.dto.AuthorDto;
import com.example.simpleproject.model.Author;
import com.example.simpleproject.repository.AuthorRepository;
import com.example.simpleproject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    @Autowired
    private AuthorConverter authorConverter;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void insertAuthor(AuthorDto authorDto) throws ParseException {
        authorRepository.save(authorConverter.toAuthor(authorDto));
    }

    public List<AuthorDto> findAllAuthor() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> authorDtos = authors.stream().map(e -> authorConverter.toAuthorDto(e)).collect(Collectors.toList());
        return authorDtos;
    }

}
