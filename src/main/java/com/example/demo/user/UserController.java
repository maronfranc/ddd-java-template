package com.example.demo.user;

import com.example.demo.hello.HelloWorldResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping(path = "/ping")
    public String ping() {
        return "Pong";
    }

    @PostMapping
    public RegisterUserResponse createUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return new RegisterUserResponse("TODO");
    }

    @GetMapping
    public String getUserList() {
        return "TODO";
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//    public String deleteUser(@PathVariable String id) {
//            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, id);
        return ResponseEntity.noContent().build();
    }
}
