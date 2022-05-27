package com.globant.gotapi.util;

import com.globant.gotapi.domain.Book;

import java.util.List;

public class TestDataBuilder {

    private TestDataBuilder() {
    }

    public static Book createTestBook() {
        Book book = new Book();
        book.setName("A Game of Thrones");
        book.setAuthors(List.of("George R. R. Martin"));
        book.setIsbn("978-0553103540");
        book.setNumberOfPages(694);
        book.setCountry("United States");
        book.setPublisher("Bantam Books");
        return book;
    }


}
