package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "UsersTable") 
public class User extends BaseModel {

	
    @Size(max = 10)
	private String firstName;
    
    public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	@Size(max = 10)
    private String lastName;
    
    @Size(max = 10)
    @Pattern(regexp="[a-Z0-9-.]{5-10}",message="Invalid username format!")
    private String userName;
    
    @Email
    private String email;
    
    @JsonIgnore
    private String password;
    
    @JsonIgnore
    public Long getId() {
    	return id;
    }
    
    
    public User(User user) {
    	this.email=user.email;
    	this.firstName=user.firstName;
    	this.lastName=user.lastName;
    	this.password=user.password;
    	this.roles=user.roles;
    	this.userName=user.userName;
    			
    }
    
    
    @JsonIgnore
	@ManyToMany
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "userId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "roleId", referencedColumnName = "id")) 
    private List<Role> roles;
	
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
   private List<Post> userPosts;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="user") 
    private List<Comment> comments;

	public List<Post> getUserPosts() {
		return userPosts;
	}


	public void setUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	




}
