package com.example.myblogapplication.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post model information")
@Data
public class PostDTO {
    @ApiModelProperty(value = "Blog post id")
    private Integer id;

    @ApiModelProperty(value = "Blog post title")
    @NotBlank(message = "Title should not be null or has at least a non-blank character")
    @Size(min = 2, message = "Post tittle should have at least 2 characters")
    private String title;

    @ApiModelProperty(value = "Blog post description")
    @NotBlank(message = "Description should not be null or has at least a non-blank character")
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @ApiModelProperty(value = "Blog post content")
    @NotBlank(message = "Content should not be null or has at least a non-blank character")
    private String content;

    @ApiModelProperty(value = "Blog post comments")
    private Set<CommentDTO> comments;

}
