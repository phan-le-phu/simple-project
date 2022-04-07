package com.example.simpleproject.config;

import com.example.simpleproject.dto.AuthorDto;
import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.model.Author;
import com.example.simpleproject.model.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<AuthorDto, Author> authorDtoAuthorTypeMap = modelMapper.createTypeMap(AuthorDto.class, Author.class);
        authorDtoAuthorTypeMap.addMappings(mapper -> mapper.skip(Author::setDob));

        TypeMap<Author, AuthorDto> authorAuthorDtoTypeMap = modelMapper.createTypeMap(Author.class, AuthorDto.class);
        authorAuthorDtoTypeMap.addMappings(mapper -> mapper.skip(AuthorDto::setStrDob));

        TypeMap<Book, BookDto> bookBookDtoTypeMap = modelMapper.createTypeMap(Book.class, BookDto.class);
        bookBookDtoTypeMap.addMappings(mapper -> mapper.skip(BookDto::setAuthorName));


        return  modelMapper;
    }
}
