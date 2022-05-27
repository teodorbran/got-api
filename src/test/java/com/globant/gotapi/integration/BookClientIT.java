package com.globant.gotapi.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.gotapi.domain.Book;
import com.globant.gotapi.rest.client.BookClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static java.nio.charset.Charset.defaultCharset;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.util.StreamUtils.copyToString;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class BookClientIT {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private BookClient bookClient;

    @Test
    public void whenGetBooks_thenTheCorrectBooksShouldBeReturned() throws IOException {
        List<Book> actualBooks = bookClient.getBooks(1, 10);
        assertThat(actualBooks.size()).isEqualTo(2);
        assertTrue(actualBooks.containsAll(getExpectedBooks()));
    }

    @Test
    public void whenGetBookById_thenTheCorrectBookShouldBeReturned() throws IOException {
        Book actualBook = bookClient.getBook(1L);
        assertThat(actualBook).isNotNull();
        assertThat(actualBook).isEqualTo(getExpectedBook());
    }

    private List<Book> getExpectedBooks() throws IOException {
        String jsonArray = copyToString(BookClientIT.class.getClassLoader().getResourceAsStream("payload/get-books-response.json"),
                defaultCharset());
        return OBJECT_MAPPER.readValue(jsonArray, new TypeReference<>() {
        });
    }

    private Book getExpectedBook() throws IOException {
        String jsonArray = copyToString(BookClientIT.class.getClassLoader().getResourceAsStream("payload/get-book-by-id-response.json"),
                defaultCharset());
        return OBJECT_MAPPER.readValue(jsonArray, new TypeReference<>() {
        });
    }
}
