package com.example.myblogapplication.controller;

import com.example.myblogapplication.payload.CommentDTO;
import com.example.myblogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable int postId, @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.OK);
    }

    @GetMapping("posts/{postId}/comments")
    public List<CommentDTO> getCommentByPostId(@PathVariable int postId) {
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable int postId, @PathVariable int commentId) {
        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
    @PatchMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int postId, @PathVariable int commentId, @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedCommentDTO = commentService.updateComment(postId, commentId, commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
    @DeleteMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int postId, @PathVariable int commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }
}
