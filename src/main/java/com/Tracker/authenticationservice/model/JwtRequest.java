package com.Tracker.authenticationservice.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JwtRequest
{
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Please enter your password")
    private String userPassword;
}
