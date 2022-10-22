package com.example.myblogapplication.service.impl;



import com.example.myblogapplication.exception.ResourceNotFoundException;
import com.example.myblogapplication.model.Post;
import com.example.myblogapplication.payload.PostDTO;
import com.example.myblogapplication.repository.PostRepository;
import com.example.myblogapplication.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }



    @Override
    public PostDTO createPost(PostDTO postDTO) {
        // convert DTO -> entity
        Post post = mapToEntity(postDTO);
        Post newPost = postRepository.save(post);

        // entity -> DTO
        PostDTO postResponse = mapToDTO(newPost);
        return postResponse;
    }
    private PostDTO mapToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
    private Post mapToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());
        post.setTitle(postDTO.getTitle());
        return post;
    }
    @Override
    public List<PostDTO> findAllPosts() {
        List<Post> posts = postRepository.findAll();
       return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }


    @Override
    public PostDTO updatePost(PostDTO postDTO, int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }


}
