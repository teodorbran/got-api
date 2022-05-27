package com.globant.gotapi.service;

import com.globant.gotapi.domain.Book;
import com.globant.gotapi.rest.client.BookClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookClient bookClient;

    public BookService(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    public Page<Book> getBooks(Pageable pageable) {
        return new PageImpl<>(bookClient.getBooks(pageable.getPageNumber(), pageable.getPageSize())) ;
    }

    public Book getBook(Long id) {
        return bookClient.getBook(id);
    }
}
