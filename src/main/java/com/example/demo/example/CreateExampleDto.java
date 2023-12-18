package com.example.demo.example;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
@Builder
public class CreateExampleDto {
    @Size(min = 3, message = "Title with length greater than 3")
    String title;

    @Size(min = 10, message = "Description with length greater than 10")
    String description;

//    @Field(name = "property_type")
}
