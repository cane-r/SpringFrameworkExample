package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "RolesTable")
public class Role extends BaseModel {

	
	
	@ManyToMany(mappedBy = "roles") 
    private List<User> users;	
	

	private String roleDescription;


	public String getRoleDescription() {
		return roleDescription;
	}


	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	
	
	
}
