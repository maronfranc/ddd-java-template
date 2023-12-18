package com.maron.ddd_java_sample.domain.auth;

import java.util.Optional;

import com.maron.ddd_java_sample.infrastructure.user.Person;
//import com.maron.ddd_java_sample.infrastructure.user.UserService;
import com.maron.ddd_java_sample.infrastructure.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthService {
    @Autowired
    private UserService userService;

//    public UserWithOmittedData registerUser(String email, String password, String salt, Optional<Person> person) {
    public void registerUser(String email, String password, String salt, Optional<Person> person) {
//    const emailExists = await this.userRepository.exists({ email: user.email });
//        if (emailExists) throw new DomainException(authException['email-already-exists']);
//    const cryptoService = new CryptoService();
//        user.salt = await cryptoService.genSalt();
//        user.password = await cryptoService.hash(user.password, user.salt);
//    const createdUser = await this.userRepository.create(user);
//        return this.cleanUserSensitiveData(createdUser);
        boolean emailExists = userService.existsByEmail(email);
        if (emailExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email already exists");
        }

//        return new UserWithOmittedData(
//                email,
//                password,
//                salt,
//                person
//        );
    }
    public void login() {}
    public void generateToken() {}
    private void handleLoginSensitiveData() {}
    private void deleeteUserSensitiveData() {}
    private void cleanUserSensitiveData() {}
}
