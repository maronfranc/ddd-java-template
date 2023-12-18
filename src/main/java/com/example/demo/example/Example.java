package com.example.demo.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Example {
    public Example(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;
//    @Field(name = "property_type")
}
