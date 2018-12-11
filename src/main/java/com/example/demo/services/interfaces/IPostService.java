package com.example.demo.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.models.Post;
import com.example.demo.models.User;

public interface IPostService { 
	
    public Page<Post> getAllPosts(Pageable request);
	
	public Post getPostById(Long id);
	
	public Post create(Post comment);
	
	public Post update(Long postId,Post post);
	
	public Post delete(Long postId);
	
	public User getUserFromPost(Long postId);

}
