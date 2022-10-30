package com.example.myblogapplication.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDTO {

    private int id;
    @NotBlank(message = "Name should not be null or has at least a non-blank character")
    private String username;

    @NotBlank(message = "Email should not be null or has at least a non-blank character")
    @Email
    private String email;

    @NotBlank(message = "Body should not be null or has at least a non-blank character")
    @Size(min = 10, max = 1000, message = "Comment body must be minimum 10 characters and not more than 1000 ones")
    private String body;
}
