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

import com.example.demo.exceptions.CommentNotFoundException;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.models.Comment; 
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.services.interfaces.ICommentService;
import com.example.demo.services.interfaces.IPostService;

@RestController("commentController")
public class CommentController {
	
	private final IPostService postService;
	private final ICommentService commentService;
    private final Authentication authentication; 
	
	public CommentController(IPostService postService,ICommentService commentService) {
		super();
		this.commentService=commentService;
		this.postService = postService;
		this.authentication= SecurityContextHolder.getContext().getAuthentication();
	}
	
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable Long postId,
                                                Pageable pageable) {
        return commentService.getAllCommentsByPostId(postId, pageable);
    }
	
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	 @PostMapping("/posts/{postId}/comments")
	    public Comment createComment(@PathVariable Long postId,
	                                 @Valid @RequestBody Comment comment) {
	      
	        comment.setPost(postService.getPostById(postId));
	        return commentService.create(postId, comment);
	    }
	 @PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	 @PutMapping("/posts/{postId}/comments/{commentId}")
	    public ResponseEntity<Comment> updateComment(@PathVariable (value = "postId") Long postId,
	                                 @PathVariable (value = "commentId") Long commentId,
	                                 @Valid @RequestBody Comment comment) {
	      if(!commentService.getCommentById(commentId).getUser().equals((((User)authentication.getPrincipal()).getId()))) {
	    	  ResponseEntity.badRequest().build();
	      }
           return ResponseEntity.ok(commentService.update(postId, commentId, comment));
	 }
	 
	 @PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	 @DeleteMapping("/posts/{postId}/comments/{commentId}")
	    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
	                              @PathVariable (value = "commentId") Long commentId) {
		 if(!commentService.getCommentById(commentId).getUser().equals((((User)authentication.getPrincipal()).getId()))) {
	    	  ResponseEntity.badRequest().build();
	      }
	 commentService.delete(postId, commentId);
	 return ResponseEntity.ok().build();
	 }

}
