package com.example.myblogapplication.repository;


import com.example.myblogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


// можно не писать аннрепозиторий, потому что жпарепоситори имплементируется от другого репо с аннотацией репозитория.
public interface PostRepository extends JpaRepository<Post, Integer> {
}
