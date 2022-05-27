package com.globant.gotapi.cucumber;

import com.globant.gotapi.domain.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TestContext {

    private ResponseEntity latestBookResponse;

    public ResponseEntity getLatestBookResponse() {
        return latestBookResponse;
    }

    public void setLatestBookResponse(ResponseEntity latestBookResponse) {
        this.latestBookResponse = latestBookResponse;
    }
}
