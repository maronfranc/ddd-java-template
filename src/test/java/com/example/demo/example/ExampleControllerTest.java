package com.example.demo.example;

import com.example.demo.user.RegisterUserDto;
import org.junit.jupiter.api.Test;
import com.example.demo.user.RegisterUserResponse;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleControllerTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createShouldInsert() throws Exception {
        String controllerUrl = "http://localhost:" + port + "/examples";
        String exampleTitle = "My example title";
//        CreateExampleDto createExamplePayload = new CreateExampleDto(exampleTitle);
        CreateExampleDto createExamplePayload = new CreateExampleDto(
                "Example title", "Example descriptions");

//        ResponseEntity<Example> registerEntityResponse = this.restTemplate.postForEntity(
//                controllerUrl, createExamplePayload, Example.class);
//        Example registerResponse = registerEntityResponse.getBody();

//        Example registerResponse = this.restTemplate.postForObject(
//                controllerUrl, createExamplePayload, Example.class);
//        ResponseEntity<CreateExampleResponse> createEntity = this.restTemplate
//                .postForEntity(controllerUrl, createExamplePayload, CreateExampleResponse.class);
        ResponseEntity<String> createEntity = this.restTemplate
                .postForEntity(controllerUrl, createExamplePayload, String.class);
        String body = createEntity.getBody();

        System.out.println("12738912793 2193712 987398127398127398127983127983");
        System.out.println("12738912793 2193712 987398127398127398127983127983");
        System.out.println("12738912793 2193712 987398127398127398127983127983");
//        System.out.println(registerEntityResponse);
        System.out.println("PAYLO: " + createExamplePayload);
        System.out.println("RESPO: " + createEntity);
        System.out.println("BODY:  " + createEntity.getBody());
//        System.out.println("E    : " + new CreateExampleDto(exampleTitle).title);
        System.out.println("12738912793 2193712 987398127398127398127983127983");
        System.out.println("12738912793 2193712 987398127398127398127983127983");
        System.out.println("12738912793 2193712 987398127398127398127983127983");
        assertThat(createEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(body).isNotNull();
//        assertThat(body)
//                .extracting("id", as(InstanceOfAssertFactories.STRING))
//                .isNotEmpty();
//        assertThat(body)
//                .extracting("title", as(InstanceOfAssertFactories.STRING))
//                .isEqualTo(exampleTitle);
//        String id = body.id();
//        String idUrl = controllerUrl + "/" + id;
        String idUrl = controllerUrl + "/" + "id";

        String response = this.restTemplate.getForObject(idUrl, String.class);
        assertThat(response).contains("TODO");

        ResponseEntity<Void> deleteEntity = this.restTemplate.exchange(idUrl, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertThat(deleteEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
