package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.services.interfaces.IPostService;

@RestController("postController")
public class PostController {
	
	private final IPostService postService;
    private final Authentication authentication; 
	
	public PostController(IPostService postService) {
		super();
		this.postService = postService;
		this.authentication= SecurityContextHolder.getContext().getAuthentication();
	}
	
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }
	
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
        		
        		
    }

	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postService.create(post);

    }
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        if(!postRequest.getUser().getId().equals(((User)authentication.getPrincipal()).getId())) {
        	//or #userId == principal.id
        	return null;//cant tamper with other users posts.
        }
		return postService.update(postId, postRequest) ;
        		
    }

	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
		Post postRequest=postService.getPostById(postId);
		if(!postRequest.getUser().getId().equals(((User)authentication.getPrincipal()).getId())) {
        	return null;//cant tamper with other users posts.
        }
        postService.delete(postId);
        return ResponseEntity.ok().build();
        		
    }
	
	

}
