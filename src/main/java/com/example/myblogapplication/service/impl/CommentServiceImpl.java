package com.example.myblogapplication.service.impl;

import com.example.myblogapplication.exception.BlogAPIException;
import com.example.myblogapplication.exception.ResourceNotFoundException;
import com.example.myblogapplication.model.Comment;
import com.example.myblogapplication.model.Post;
import com.example.myblogapplication.payload.CommentDTO;
import com.example.myblogapplication.repository.CommentRepository;
import com.example.myblogapplication.repository.PostRepository;
import com.example.myblogapplication.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO createComment(int postId, CommentDTO commentDTO) {
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

    @Override
    public CommentDTO getCommentById(int postId, int commentId) {
        // retrieve post enitity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't to this post");
        }

        return mapToDTO(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(int postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comments
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(int postId, int commentId, CommentDTO commentRequest) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to this post");
        }
        comment.setUsername(commentRequest.getUsername());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

       Comment updatedComment = commentRepository.save(comment);
       return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(int postId, int commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to this post");
        }
        commentRepository.delete(comment);
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


}
