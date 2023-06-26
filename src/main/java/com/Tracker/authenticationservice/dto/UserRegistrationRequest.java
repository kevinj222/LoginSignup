package com.screenspot.authenticationservice.dto;

import com.screenspot.authenticationservice.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest
{
    @NotEmpty(message = "First name is required")
    private String name;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    @ValidPassword(message = "Password criteria does not match")
    private String userPassword;
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    @Pattern(regexp = ".*[a-zA-Z]\\.com$", message = "Email must be of format @abc.com")
    private String email;
}
