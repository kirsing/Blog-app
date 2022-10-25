package com.example.myblogapplication.payload;

import lombok.Data;

@Data
public class CommentDTO {
    private int id;
    private String username;
    private String email;
    private String body;
}
