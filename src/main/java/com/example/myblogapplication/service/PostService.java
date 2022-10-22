package com.example.myblogapplication.service;





import com.example.myblogapplication.payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> findAllPosts();

    PostDTO getPostById(int id);

    PostDTO updatePost(PostDTO postDTO, int id);

    void deletePostById(int id);
}
