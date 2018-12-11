package com.example.demo.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Created by caner on 20/07/18.
 */
@Entity
@Table(name = "PostCommentsTable")
public class Comment extends BaseModel { 

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
	
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
	
	@NotNull
    @Lob
    private String commentContent;
    
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);//bidirectional
    }
}
