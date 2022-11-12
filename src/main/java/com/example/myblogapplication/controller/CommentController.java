package com.example.myblogapplication.controller;

import com.example.myblogapplication.payload.CommentDTO;
import com.example.myblogapplication.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "CRUD REST APIs for Comment resources")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Create Comment REST API")
    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable int postId, @Valid @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.OK);
    }
    @ApiOperation(value = "Get all Comments by Post ID REST API")
    @GetMapping("posts/{postId}/comments")
    public List<CommentDTO> getCommentByPostId(@PathVariable int postId) {
        return commentService.getCommentsByPostId(postId);
    }
    @ApiOperation(value = "Get Single Comment by Post ID REST API")
    @GetMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable int postId, @PathVariable int commentId) {
        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
    @ApiOperation(value = "UPDATE Comment by ID REST API")
    @PatchMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int postId, @PathVariable int commentId, @Valid @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedCommentDTO = commentService.updateComment(postId, commentId, commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
    @ApiOperation(value = "DELETE Comment by ID REST API")
    public ResponseEntity<String> deleteComment(@PathVariable int postId, @PathVariable int commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }
}
