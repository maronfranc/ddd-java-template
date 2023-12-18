package com.example.demo.hello;

import com.example.demo.example.CreateExampleDto;
import com.example.demo.example.Example;
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

    public void greetingShouldReturnDefaultMessage() throws Exception {
        String url = "http://localhost:" + port + "/hello-world";
        String response = this.restTemplate.getForObject(url, String.class);
        assertThat(response).contains("Hello World");
    }
}
