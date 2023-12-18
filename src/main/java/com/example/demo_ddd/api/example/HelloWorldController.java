package com.maron.ddd_java_sample.api.example;

import com.maron.ddd_java_sample.api.example.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/hello-world")
//@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
public class HelloWorldController {
    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World From a Java Bean");
    }

    @GetMapping(path = "/world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        // throw new RuntimeException("Something went wrong");
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
