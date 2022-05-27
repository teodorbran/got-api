package com.globant.gotapi.rest.client;

import com.globant.gotapi.domain.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "books", url = "${got.service.url}")
public interface BookClient {

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    List<Book> getBooks(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize);

    @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
    Book getBook(@PathVariable("id") Long id);
}