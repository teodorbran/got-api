package com.globant.gotapi.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

@TestConfiguration
public class WireMockConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockBooksService() throws IOException {
        WireMockServer mockBooksService = new WireMockServer(9561);
        mockBooksService.stubFor(WireMock.get(WireMock.urlEqualTo("/books?page=1&pageSize=10"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        WireMockConfig.class.getClassLoader().getResourceAsStream("payload/get-books-response.json"),
                                        defaultCharset()))));
        mockBooksService.stubFor(WireMock.get(WireMock.urlEqualTo("/books/1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        WireMockConfig.class.getClassLoader().getResourceAsStream("payload/get-book-by-id-response.json"),
                                        defaultCharset()))));
        return mockBooksService;
    }
}
