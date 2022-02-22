package com.example.myShop.integration;

import com.example.myShop.domain.dto.security.LoginRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.io.InputStream;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

/**
 * @author nafis
 * @since 22.02.2022
 */

@ActiveProfiles("TEST")
@AutoConfigureWebTestClient
@DirtiesContext(classMode = BEFORE_CLASS)
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///test",
                "spring.session.datasource.jdbc-url=jdbc:tc:postgresql:14-alpine:///test",
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
        })
public abstract class BaseTest {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebTestClient webTestClient;

    protected String token;

    protected <T> T getClassPathResourceAsObject(String path, TypeReference<T> reference){
        try {
            InputStream resource = new ClassPathResource(path).getInputStream();
            return objectMapper.readValue(resource, reference);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    @BeforeEach
    public void login() {
        token = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/auth/login").build())
                .bodyValue(LoginRequest.builder().username("username@bk.ru").password("1234ytr").build())
                .exchange()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();
        webTestClient = webTestClient.mutate()
                .defaultHeaders(httpHeaders -> httpHeaders.add("AUTHORIZATION", token))
                .build();
    }
}
