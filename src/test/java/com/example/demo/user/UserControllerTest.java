package com.example.demo.user;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

//import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void registerUserDtoShouldValidateFields() throws Exception {
        String controllerUrl = "http://localhost:" + port + "/users";
        String invalidEmail = "invalid email";
        String validEmail = "valid@email.com";
        String validPassword = "Password with length greater than 6";
        String invalidPassword = "12345";

        RegisterUserDto invalidEmailPayload = new RegisterUserDto(invalidEmail, validPassword);
        ResponseEntity<RegisterUserResponse> invalidEmailEntity = this.restTemplate
                .postForEntity(controllerUrl, invalidEmailPayload, RegisterUserResponse.class);
        assertThat(invalidEmailEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        RegisterUserDto invalidPasswordPayload = new RegisterUserDto(validEmail, invalidPassword);
        ResponseEntity<RegisterUserResponse> invalidPasswordEntity = this.restTemplate
                .postForEntity(controllerUrl, invalidPasswordPayload, RegisterUserResponse.class);
        assertThat(invalidPasswordEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void userCreateAnDelete() throws Exception {
        String controllerUrl = "http://localhost:" + port + "/users";
        String newEmail = "email@test.com";
        RegisterUserDto createPayload = new RegisterUserDto(
                newEmail, "pass123"
        );
        ResponseEntity<RegisterUserResponse> registerEntityResponse = this.restTemplate.postForEntity(
                controllerUrl, createPayload, RegisterUserResponse.class);
        RegisterUserResponse registerResponse = registerEntityResponse.getBody();

        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse)
                .extracting("id", as(InstanceOfAssertFactories.STRING))
                .isNotEmpty();
        String id = registerResponse.id();

        String response = this.restTemplate.getForObject(controllerUrl, String.class);
        assertThat(response).contains("TODO");

        String deleteUrl = controllerUrl + "/" + id;
        ResponseEntity<Void> deleteEntity = this.restTemplate.exchange(deleteUrl, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        System.out.println(deleteEntity);
        assertThat(deleteEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
