package com.example.demo.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.models.Comment;

public interface ICommentService { 
 
	
	public Page<Comment> getAllCommentsByPostId(Long id,Pageable request);
	
	public Comment getCommentById(Long id);
	
	public Comment create(Long id,Comment comment);
	
	public Comment update(Long postId,Long commentId,Comment comment);
	
	public Comment delete(Long postId,Long commentId);
	
}
