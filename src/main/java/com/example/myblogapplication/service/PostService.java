package com.example.myblogapplication.service;





import com.example.myblogapplication.payload.PostDTO;
import com.example.myblogapplication.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostResponse findAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection);

    PostDTO getPostById(int id);

    PostDTO updatePost(PostDTO postDTO, int id);

    void deletePostById(int id);
}
