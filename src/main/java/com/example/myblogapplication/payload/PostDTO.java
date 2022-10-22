package com.example.myblogapplication.payload;

import lombok.Data;

@Data
public class PostDTO {
    private Integer id;
    private String title;
    private String description;
    private String content;


}
