package com.example.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class RegisterUserDto {
    @Email(message = "Your login email")
    final String email;

    @Size(min = 6, message = "Password with length greater than 6")
    final String password;

//    @Field(name = "property_type")
}
