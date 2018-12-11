package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> { 

}
