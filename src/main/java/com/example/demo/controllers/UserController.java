package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.services.interfaces.IUserService;

 
@RestController("userController") 
public class UserController {
	
	private final IUserService userService;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN's)")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }
	
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN)")
	@PostMapping("/users/")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        
       return ResponseEntity.ok(userService.create(user));
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN)")
	@PutMapping("/users/{userId}")
	public User updateUser(@PathVariable Long userId,@Valid @RequestBody User user) {
       
        return userService.update(userId,user);
    }
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN') && #userId == principal.id")
	@DeleteMapping("/users/{userId}")
	public User deleteUserById(@PathVariable Long userId) {
        return userService.delete(userId);
    }
	
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/users/{userId}/posts")
	public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return userService.getPostsByUserId(userId);
    }
	
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/users/{userId}/comments")
	public List<Comment> getCommentsByUserId(@PathVariable Long userId) {
        return userService.getCommentsByUserId(userId);
    }
	
}
