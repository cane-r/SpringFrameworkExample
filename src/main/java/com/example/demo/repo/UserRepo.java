package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> { 
         
	     Optional<User> findByUserName(String userName);
	     Optional<User> findByEmail(String email);
}
