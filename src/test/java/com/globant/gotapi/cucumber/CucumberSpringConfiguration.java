package com.globant.gotapi.cucumber;

import com.globant.gotapi.integration.WireMockConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Import(WireMockConfig.class)
public class CucumberSpringConfiguration {
}
