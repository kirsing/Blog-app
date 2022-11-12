package com.example.myblogapplication.controller;



import com.example.myblogapplication.payload.PostDTO;
import com.example.myblogapplication.payload.PostResponse;
import com.example.myblogapplication.service.PostService;
import com.example.myblogapplication.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Api(value = "CRUD REST APIs for Post resources")
@RestController
@RequestMapping
@AllArgsConstructor

public class PostController {

    private PostService postService;

    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get all Posts REST API")
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDirection
            ) {
        return postService.findAllPosts(pageNo, pageSize, sortBy, sortDirection);
    }
    @ApiOperation(value = "Get Post by ID REST API")
    @GetMapping( "/api/posts/v1/{id}")
        public ResponseEntity<PostDTO> getPostByIdV1(@PathVariable(name = "id") int id) {
            return ResponseEntity.ok(postService.getPostById(id));
    }
    @ApiOperation(value = "UPDATE Post by ID REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable int id) {
        PostDTO postResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @ApiOperation(value = "DELETE Post by ID REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

}
