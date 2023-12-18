package com.maron.ddd_java_sample.domain.auth;

import com.maron.ddd_java_sample.infrastructure.user.Person;

import java.util.Optional;
import java.util.UUID;

public record UserWithOmittedData (
        UUID id,
        String email,
        String password,
        String salt,
        Optional<Person> person
) { }
