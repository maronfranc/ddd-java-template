package com.example.demo.example;

import com.example.demo.user.RegisterUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/examples")
public class ExampleController {
    @Autowired
    private ExampleRepository exampleRepository;

    @PostMapping
//    public Example createExample(@Valid @RequestBody CreateExampleDto exampleDto) {
//    public CreateExampleResponse createExample(@Valid @RequestBody CreateExampleDto exampleDto) {
//    public String createExample(@Valid @RequestBody RegisterUserDto exampleDto) {
    public CreateExampleResponse createExample(@Valid @RequestBody CreateExampleDto dto) {
//        return new Example("TODO", exampleDto.title);
//        return "1908247 1982749812 798412";
//        String createdId = this.exampleRepository.insert(dto.title, dto.description);
        return new CreateExampleResponse("createdId");
    }

    @GetMapping(path = "/{id}")
    public List<Example> listExample(@PathVariable String id) {
        return List.of(new Example("TODO:LIST", "TODO:LIST TITLE"));
    }

    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<Void> deleteExample(@PathVariable String id) {
    public String deleteExample(@PathVariable String id) {
//        return ResponseEntity.noContent().build();
        return "TODO:DELETE";
    }
}
