package com.maron.ddd_java_sample.api.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/examples")
public class ExampleController {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public String getById(@PathVariable String id) {
        return id;
    }


    @GetMapping("/")
    public String getMany(@PathVariable String id) {
        return id;
    }

    @GetMapping("/ping")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
