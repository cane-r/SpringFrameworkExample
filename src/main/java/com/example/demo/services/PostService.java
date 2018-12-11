package com.example.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repo.PostRepo;
import com.example.demo.services.interfaces.IPostService;

public class PostService implements IPostService { 

	private final PostRepo postRepo;
	
	
	
	public PostService(PostRepo postRepo) {
		super();
		this.postRepo = postRepo;
	}

	@Override
	public Page<Post> getAllPosts(Pageable request) {
		// TODO Auto-generated method stub
		return postRepo.findAll(request);
	}

	@Override
	public Post getPostById(Long postId) {
		return postRepo.findById(postId).orElseThrow(
		() -> new PostNotFoundException("Post with id " + postId + " could not be found!"));
	}

	@Override
	public Post delete(Long postId) {
		// TODO Auto-generated method stub
		return postRepo.findById(postId).map(post ->{
		postRepo.delete(post);
		return post;
		}).orElseThrow(
		()-> new PostNotFoundException("Post with id " + postId + " could not be found!"));
				
		
	}

	@Override
	public Post create(Post post) {
		return postRepo.saveAndFlush(post);
	}

	@Override
	public Post update(Long postId,Post postParam) {
		return postRepo.findById(postId).map(post->{
          post.setPostContent(postParam.getPostContent());
		  post.setPostTitle(postParam.getPostTitle());
		  return postRepo.save(post);
		}).orElseThrow(
		()	-> new PostNotFoundException("Post with id " + postId + " could not be found!"));
					
	}

	@Override
	public User getUserFromPost(Long postId) {
		return postRepo.findById(postId).orElseThrow(
				() -> new PostNotFoundException("Post with id " + postId + " could not be found!"))
				.getUser();

	}

}
