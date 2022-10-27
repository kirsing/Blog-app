package com.example.myblogapplication.service;

import com.example.myblogapplication.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(int postId, CommentDTO commentDTO);

    CommentDTO getCommentById(int postId, int commentId);

    List<CommentDTO> getCommentsByPostId(int postId);

    CommentDTO updateComment(int postId, int commentId, CommentDTO commentRequest);
}
