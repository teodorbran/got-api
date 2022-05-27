package com.globant.gotapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Book {

    private String url;
    private String name;
    private String isbn;
    private List<String> authors;
    private int numberOfPages;
    private String publisher;
    private String country;
    private String mediaType;
    private String released;
    private List<String> characters;
    private List<String> povCharacters;
}
