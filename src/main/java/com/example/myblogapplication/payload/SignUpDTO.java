package com.example.myblogapplication.payload;

import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<String> role;
}
