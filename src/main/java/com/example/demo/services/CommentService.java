package com.example.demo.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.exceptions.CommentNotFoundException;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.repo.CommentRepo;
import com.example.demo.repo.PostRepo;
import com.example.demo.services.interfaces.ICommentService;

public class CommentService implements ICommentService 
{
	private final CommentRepo commentRepo;
	private final PostRepo postRepo;
	
	
	public CommentService(CommentRepo repo,PostRepo postRepo) {
		this.commentRepo=repo;
		this.postRepo=postRepo;
		
	}

	@Override
	public Page<Comment> getAllCommentsByPostId(Long postId, Pageable request) {
		// TODO Auto-generated method stub
		return commentRepo.findByPostId(postId, request);
	}
	
	
	@Override
	public Comment getCommentById(Long commentId) {
		return commentRepo.findById(commentId).orElseThrow(
	()->new CommentNotFoundException("Comment with id + " + commentId + " could not be found!"));
	}
	

	@Override
	public Comment create(Long postId, Comment comment) {
	final Optional<Post> post=postRepo.findById(postId);
	if(!post.isPresent()) {
         throw new PostNotFoundException("Post with id + " + postId + " could not be found!");
	}
	comment.setPost(post.get());
	return commentRepo.save(comment);
	
	// or postRepo.findById(postId).map(post->{comment.setPost(post); return commentRepo.save(comment))}).orElseThrow(...);
	
		
	}

	@Override
	public Comment update(Long postId, Long commentId, Comment comment) {
		final Optional<Post> post=postRepo.findById(postId);
		if(!post.isPresent()) {
	         throw new PostNotFoundException("Post with id + " + postId + " could not be found!");
	}
		Optional<Comment> mcomment=commentRepo.findById(commentId);
		if(!mcomment.isPresent()) {
			throw new CommentNotFoundException("Comment with id + " + postId + " could not be found!");
		}
		Comment m=mcomment.get();
		m.setCommentContent(comment.getCommentContent());
		return commentRepo.save(m);

}

	@Override
	public Comment delete(Long postId, Long commentId) {
		Post post=postRepo.findById(postId).orElseThrow(
				() -> new PostNotFoundException("Post with id + " + postId + " could not be found!"));
		
		return commentRepo.findById(commentId).map(comment -> {
		  commentRepo.delete(comment);
			return comment;
		}).orElseThrow(() -> new CommentNotFoundException("Comment with id + " + postId + " could not be found!"));
	}

	
	
}
