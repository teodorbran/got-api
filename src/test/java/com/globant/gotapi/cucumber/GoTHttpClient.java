package com.globant.gotapi.cucumber;

import com.globant.gotapi.domain.Book;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class GoTHttpClient {
    private final String SERVER_URL = "http://localhost";
    private final String BOOKS_ENDPOINT = "/api/books";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String booksEndpoint(Long id) {
        return SERVER_URL + ":" + port + BOOKS_ENDPOINT + "/" + (id == null ? "" : id.toString());
    }

    public ResponseEntity<PaginatedResponse<Book>> getBooks() {
        ParameterizedTypeReference<PaginatedResponse<Book>> responseType = new ParameterizedTypeReference<PaginatedResponse<Book>>() { };
        return restTemplate.exchange(booksEndpoint(null), HttpMethod.GET, null, responseType);
    }

    public ResponseEntity<Book> getBook(Long id) {
        return restTemplate.exchange(booksEndpoint(id), HttpMethod.GET, null, Book.class);
    }

    public void clean() {
        restTemplate.delete(booksEndpoint(null));
    }
}
