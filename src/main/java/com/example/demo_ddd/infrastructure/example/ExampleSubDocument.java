package com.maron.ddd_java_sample.infrastructure.example;

import jakarta.persistence.*;

public class ExampleSubDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
}
