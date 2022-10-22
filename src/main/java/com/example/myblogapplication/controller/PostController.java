package com.example.myblogapplication.controller;



import com.example.myblogapplication.payload.PostDTO;
import com.example.myblogapplication.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;


    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.findAllPosts();
    }
    @GetMapping("/{id}")
        public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") int id) {
            return ResponseEntity.ok(postService.getPostById(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable int id) {
        PostDTO postResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

}
