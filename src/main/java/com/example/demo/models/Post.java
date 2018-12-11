package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "PostsTable")
public class Post extends BaseModel {

	@NotNull
    @Size(max = 50)
    @Column(unique = true)
    private String postTitle; 

    @NotNull
    @Lob//Large binary object for text representation..
    private String postContent;
    
    public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name="UserIds")
    private User user;

public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//need to provide this behalf of lombok getter because bidirectional mapping requires so.(in comment.setPost() method)
	public Set<Comment> getComments() {
		return comments;
	}

            
  
    
}
