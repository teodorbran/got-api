package com.globant.gotapi.controller;

import com.globant.gotapi.domain.Book;
import com.globant.gotapi.service.BookService;
import com.globant.gotapi.util.TestDataBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void whenGetBooks_ThenTheCorrectBooksShouldBeReturned() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(TestDataBuilder.createTestBook());
        Mockito.when(bookService.getBooks(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(books));
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].name", Matchers.equalTo(books.get(0).getName())))
                .andExpect(jsonPath("$.content[0].authors[0]", Matchers.equalTo(books.get(0).getAuthors().get(0))))
                .andExpect(jsonPath("$.content[0].isbn", Matchers.equalTo(books.get(0).getIsbn())))
                .andExpect(jsonPath("$.content[0].numberOfPages", Matchers.equalTo(books.get(0).getNumberOfPages())));
    }

    @Test
    public void whenGetBookWithId1_ThenTheCorrectBookIsReturned() throws Exception {
        Book expectedBook = TestDataBuilder.createTestBook();
        Mockito.when(bookService.getBook(1L)).thenReturn(expectedBook);
        mockMvc.perform(get("/api/books/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo(expectedBook.getName())))
                .andExpect(jsonPath("$.authors[0]", Matchers.equalTo(expectedBook.getAuthors().get(0))))
                .andExpect(jsonPath("$.isbn", Matchers.equalTo(expectedBook.getIsbn())))
                .andExpect(jsonPath("$.numberOfPages", Matchers.equalTo(expectedBook.getNumberOfPages())));
    }
}
