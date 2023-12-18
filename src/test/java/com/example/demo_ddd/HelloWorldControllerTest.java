package com.maron.ddd_java_sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
//        String response = this.restTemplate.getForObject("http://localhost:" + port + "/hello-world", String.class);
//        assertThat(response).contains("Hello World");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello-world",
                String.class)).contains("Hello World");
    }
}
