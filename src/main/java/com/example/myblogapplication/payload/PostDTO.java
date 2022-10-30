package com.example.myblogapplication.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDTO {
    private Integer id;

    @NotBlank(message = "Title should not be null or has at least a non-blank character")
    @Size(min = 2, message = "Post tittle should have at least 2 characters")
    private String title;

    @NotBlank(message = "Description should not be null or has at least a non-blank character")
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotBlank(message = "Content should not be null or has at least a non-blank character")
    private String content;

    private Set<CommentDTO> comments;

}
