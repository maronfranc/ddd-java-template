package com.example.demo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
//@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
public class HelloWorldController {
    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

//    @GetMapping(path = "/bean")
//    public HelloWorldResponse helloWorldBean() {
//        return new HelloWorldResponse("Hello World From a Java Bean");
//    }

//    @GetMapping(path = "/path-variable/{name}")
//    public HelloWorldResponse helloWorldPathVariable(@PathVariable String name) {
//        return new HelloWorldResponse(String.format("Hello World, %s", name));
//    }
}
