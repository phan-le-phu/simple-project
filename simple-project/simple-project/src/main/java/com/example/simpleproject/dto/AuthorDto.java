package com.example.simpleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String name;
    private String strDob;

    @Override
    public String toString() {
        return "AuthorDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strDob='" + strDob + '\'' +
                '}';
    }
}
