package com.example.simpleproject.converter;

import com.example.simpleproject.dto.AuthorDto;
import com.example.simpleproject.model.Author;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AuthorConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Author toAuthor(AuthorDto authorDto) throws ParseException {
        Author author = modelMapper.map(authorDto, Author.class);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = simpleDateFormat.parse(authorDto.getStrDob());
        author.setDob(dob);

        return author;
    }

    public AuthorDto toAuthorDto(Author author) {
        AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);

        authorDto.setStrDob(author.getDob().toString());
        return authorDto;
    }
}
