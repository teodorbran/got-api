package com.globant.gotapi.service;

import com.globant.gotapi.domain.Book;
import com.globant.gotapi.rest.client.BookClient;
import com.globant.gotapi.util.TestDataBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookClient bookClient;

    @InjectMocks
    private BookService bookService;

    @Test
    public void whenGetBooks_thenItShouldReturnTheCorrectBooks() {
        List<Book> books = new ArrayList<>();
        books.add(TestDataBuilder.createTestBook());
        when(bookClient.getBooks(1, 10))
                .thenReturn(books);
        Page<Book> result = bookService.getBooks(PageRequest.of(1, 10));
        assertThat(result.getContent()).isEqualTo(books);
    }

    @Test
    public void whenGetBookWithId1_thenItShouldReturnTheCorrectBook() {
        Book expectedBook = TestDataBuilder.createTestBook();
        when(bookClient.getBook(1L))
                .thenReturn(expectedBook);
        Book actualBook = bookService.getBook(1L);
        assertThat(actualBook).isEqualTo(expectedBook);
    }
}
