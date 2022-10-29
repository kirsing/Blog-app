package com.example.myblogapplication.payload;

import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private Integer id;
    private String title;
    private String description;
    private String content;

    private Set<CommentDTO> comments;

}
