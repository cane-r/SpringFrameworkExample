package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.interfaces.IUserService;

public class UserService implements IUserService {

	private final UserRepo userRepo; 
	
	public UserService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public Page<User> getAllUsers(Pageable request) {
		return userRepo.findAll(request);
	}

	@Override
	public  User getUserById(Long id) {
		return userRepo.findById(id).orElseThrow(
		()-> new UserNotFoundException("User with id " + id + " could not be found on this server!")		
				);
	}

	@Override
	public User create(User user) {
		return userRepo.save(user);
	}

	@Override
	public User update(Long id, User userParam) {
		return userRepo.findById(id).map(user -> {
			user=new User(userParam);
			return userRepo.save(user);
		}).orElseThrow(()->
		new UserNotFoundException("User with id " + id + " could not be found on this server!")	);
	}

	@Override
	public User delete(Long id) {
		return userRepo.findById(id).map(user -> {
		userRepo.delete(user);
		return user;
		}).orElseThrow(()->
		new UserNotFoundException("User with id " + id + " could not be found on this server!")	);
	
	}

	@Override
	public User getUserByMail(String mail) {
		return userRepo.findByEmail(mail).orElseThrow(
			() -> new UserNotFoundException("User with mail " + mail + " could not be found on this server!"));
			
	}

	@Override
	public List<Post> getPostsByUserId(Long userId) {
		return userRepo.findById(userId).map(user -> 
		{
		return user.getUserPosts();	
		}).orElseThrow(
		() -> new UserNotFoundException("User with id " + userId + " could not be found on this server!"));
		
	}

	@Override
	public List<Comment> getCommentsByUserId(Long userId) {
		return userRepo.findById(userId).map(user -> 
		{
		return user.getComments();
		}).orElseThrow(
		() -> new UserNotFoundException("User with id " + userId + " could not be found on this server!"));
	}
	
	@Override
	public User getByUsername(String username) {
		return userRepo.findByUserName(username).orElseThrow(
				() -> new UserNotFoundException("User with username " + username + " could not be found on this server!"));
	}

}
