package com.example.myblogapplication.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Api(value = "Comment model information")
@Data
public class CommentDTO {

    @ApiModelProperty(value = "Comment id")
    private int id;

    @ApiModelProperty(value = "Comment username")
    @NotBlank(message = "Name should not be null or has at least a non-blank character")
    private String username;

    @ApiModelProperty(value = "Comment e-mail")
    @NotBlank(message = "Email should not be null or has at least a non-blank character")
    @Email
    private String email;

    @ApiModelProperty(value = "Comment body")
    @NotBlank(message = "Body should not be null or has at least a non-blank character")
    @Size(min = 10, max = 1000, message = "Comment body must be minimum 10 characters and not more than 1000 ones")
    private String body;
}
