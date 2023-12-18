package com.maron.ddd_java_sample.infrastructure.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        userRepository.insert(user);
        return user; // TODO: add insert returning.
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User updateUser(String id, User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Update error: user not found in the database (id:" + id + ")");
        }

        User existingUser = user.get();
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getEmail());
        userRepository.update(existingUser);
        return existingUser;
    }

    public boolean existsByEmail(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        return existingUser.isPresent();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
