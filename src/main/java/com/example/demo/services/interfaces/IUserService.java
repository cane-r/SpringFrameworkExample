package com.example.demo.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;

public interface IUserService { 

public Page<User> getAllUsers(Pageable request);
	
	public User getUserById(Long id);
	
	public User create(User user);
	
	public User update(Long id,User user);
	
	public User delete(Long id);
	
	public User getByUsername(String username);
	
	public User getUserByMail(String mail);
	
	public List<Post> getPostsByUserId(Long userId);
	
	public List<Comment> getCommentsByUserId(Long userId);
}
