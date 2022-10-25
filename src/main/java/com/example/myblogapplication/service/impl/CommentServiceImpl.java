package com.example.myblogapplication.service.impl;

import com.example.myblogapplication.exception.ResourceNotFoundException;
import com.example.myblogapplication.model.Comment;
import com.example.myblogapplication.model.Post;
import com.example.myblogapplication.payload.CommentDTO;
import com.example.myblogapplication.repository.CommentRepository;
import com.example.myblogapplication.repository.PostRepository;
import com.example.myblogapplication.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO createPost(int postId, CommentDTO commentDTO) {
        // convert DTO -> entity
        Comment comment = mapToEntity(commentDTO);

        // retrieve post enitity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment enity
        comment.setPost(post);

        // comment entity to DB
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }
    private CommentDTO mapToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setUsername(comment.getUsername());
        return commentDTO;
    }
    private Comment mapToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setUsername(commentDTO.getUsername());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        return comment;
    }

    @Override
    public CommentDTO getCommentById(int id) {
        return null;
    }

    @Override
    public CommentDTO updateCommentById(int id) {
        return null;
    }

    @Override
    public void deletePostById(int id) {

    }
}
