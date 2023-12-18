package com.maron.ddd_java_sample.infrastructure.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExampleRepository extends JpaRepository<Example, Long> {
//    @Query("SELECT id FROM examples")
//    Example getSometing(String id);
}
