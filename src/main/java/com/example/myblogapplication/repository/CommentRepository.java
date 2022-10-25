package com.example.myblogapplication.repository;

import com.example.myblogapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
