package com.example.simpleproject.ServiceTests;

import com.example.simpleproject.converter.AuthorConverter;
import com.example.simpleproject.dto.AuthorDto;
import com.example.simpleproject.model.Author;
import com.example.simpleproject.repository.AuthorRepository;
import com.example.simpleproject.service.AuthorService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    @Autowired
    AuthorService authorService;

    @MockBean
    AuthorRepository authorRepository;

    @Autowired
    AuthorConverter authorConverter;


    @Test
    public void testFindAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1L, "phu", new Date()));
        authorList.add(new Author(2L, "tuan", new Date()));

        when(authorRepository.findAll()).thenReturn(authorList);

        Assert.isTrue(authorService.findAllAuthor().size() == 2);
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testSaveAuthor() throws ParseException {

        AuthorDto authorDto = new AuthorDto(1L, "phu", "11/2/2000");

        Author author = authorConverter.toAuthor(authorDto);
        when(authorRepository.save(author)).thenReturn(author);

        authorService.insertAuthor(authorDto);

        verify(authorRepository, times(1)).save(author);
    }
}