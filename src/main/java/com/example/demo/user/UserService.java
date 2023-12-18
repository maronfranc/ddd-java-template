package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserWithOmittedData registerUser(RegisterUserDto user) {
        boolean userExists = userRepository.existsByEmail(user.email);
        if (userExists) {
            throw new HttpServerErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "User already exists with this email(" + user.email + ")");
        }
        String userId = this.userRepository.insertReturningId(user.email, user.password);
        return new UserWithOmittedData(userId, user.email);
    }
}
