package com.apidev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidev.model.Category;
import com.apidev.model.Post;
import com.apidev.model.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String keyword);
	
}
