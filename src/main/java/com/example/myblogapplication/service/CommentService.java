package com.example.myblogapplication.service;

import com.example.myblogapplication.payload.CommentDTO;

public interface CommentService {
    CommentDTO createPost(int postId, CommentDTO commentDTO);

    CommentDTO getCommentById(int id);

    CommentDTO updateCommentById(int id);

    void deletePostById(int id);
}
