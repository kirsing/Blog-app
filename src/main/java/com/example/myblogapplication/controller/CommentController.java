package com.example.myblogapplication.controller;

import com.example.myblogapplication.payload.CommentDTO;
import com.example.myblogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable int postId, @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(commentService.createPost(postId, commentDTO), HttpStatus.OK);
    }
}
