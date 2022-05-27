package com.globant.gotapi.cucumber.stepdefinitions;

import com.globant.gotapi.cucumber.GoTHttpClient;
import com.globant.gotapi.cucumber.PaginatedResponse;
import com.globant.gotapi.cucumber.TestContext;
import com.globant.gotapi.domain.Book;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class GoTStepDefinitions {

    @Autowired
    private TestContext context;

    @Autowired
    private GoTHttpClient gotHttpClient;

    @When("the client requests all books")
    public void clientCallsGetBooks() {
        ResponseEntity<PaginatedResponse<Book>> response = gotHttpClient.getBooks();
        context.setLatestBookResponse(response);
    }

    @When("the client requests book with id {int}")
    public void clientCallsGetBookWithId1(int id) {
        ResponseEntity<Book> response = gotHttpClient.getBook((long) id);
        context.setLatestBookResponse(response);
    }

    @Then("the client receives status code of {int}")
    public void clientReceivesStatusCode(int statusCode) {
        HttpStatus currentStatusCode = context.getLatestBookResponse().getStatusCode();
        assertThat(currentStatusCode.value()).isEqualTo(statusCode);
    }

    @Then("the response contains the following books:")
    public void responseContainsBooks(DataTable dataTable) {
        List<Book> actualBooks = ((ResponseEntity<PaginatedResponse<Book>>)context.getLatestBookResponse()).getBody().getContent();
        final List<Map<String, String>> rows = dataTable.asMaps();
        rows.forEach(row -> {
            assertThat(actualBooks).extracting(Book::getName, Book::getIsbn, Book::getAuthors)
                    .contains(tuple(row.get("name"),
                            row.get("isbn"),
                            List.of(row.get("author"))));
        });
    }

    @Then("the response contains a book with name {string} and isbn {string}")
    public void responseContainsBookWithNameAndIsbn(String name, String isbn) {
        Book actualBook = ((ResponseEntity<Book>)context.getLatestBookResponse()).getBody();
        assertThat(actualBook.getName()).isEqualTo(name);
        assertThat(actualBook.getIsbn()).isEqualTo(isbn);
    }
}
